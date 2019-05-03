package caio.com.data

import caio.com.data.factory.OmdbFactory
import caio.com.data.mapper.OmdbEntityMapper
import caio.com.data.mapper.OmdbResponseEntityMapper
import caio.com.data.model.ResponseEntity
import caio.com.data.repository.OmdbDataStore
import caio.com.data.source.OmdbCacheDataStore
import caio.com.data.source.OmdbDataStoreFactory
import caio.com.data.source.OmdbRemoteDataStore
import caio.com.domain.model.Response
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OmdbDataRepositoryTest {
    private lateinit var omdbDataRepository: OmdbDataRepository
    private lateinit var omdbDataStoreFactory: OmdbDataStoreFactory
    private lateinit var omdbMapper: OmdbEntityMapper
    private lateinit var responseMapper: OmdbResponseEntityMapper
    private lateinit var omdbCacheDataStore: OmdbCacheDataStore
    private lateinit var omdbRemoteDataStore: OmdbRemoteDataStore

    @Before
    fun setUp() {
        omdbDataStoreFactory = mock()
        omdbMapper = mock()
        omdbCacheDataStore = mock()
        omdbRemoteDataStore = mock()
        responseMapper = mock()
        omdbDataRepository = OmdbDataRepository(omdbDataStoreFactory, omdbMapper, responseMapper)

        stubOmdbDataStoreFactoryRetrieveCacheDataStore()
        stubOmdbDataStoreFactoryRetrieveRemoteDataStore()
    }

    //<editor-fold desc="Clear Omdbs">
    @Test
    fun clearOmdbsCompletes() {
        stubOmdbCacheClearOmdbs(Completable.complete())
        val testObserver = omdbDataRepository.clear().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearOmdbsCallsCacheDataStore() {
        stubOmdbCacheClearOmdbs(Completable.complete())
        omdbDataRepository.clear().test()
        verify(omdbCacheDataStore).clear()
    }

    @Test
    fun clearOmdbsNeverCallsRemoteDataStore() {
        stubOmdbCacheClearOmdbs(Completable.complete())
        omdbDataRepository.clear().test()
        verify(omdbRemoteDataStore, never()).clear()
    }
    //</editor-fold>

    //<editor-fold desc="Save Omdbs">
    @Test
    fun saveOmdbsCompletes() {
        stubOmdbCacheSaveOmdbs(Completable.complete())
        val testObserver = omdbDataRepository.save(
            OmdbFactory.makeOmdbList(2)
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveOmdbsCallsCacheDataStore() {
        stubOmdbCacheSaveOmdbs(Completable.complete())
        omdbDataRepository.save(OmdbFactory.makeOmdbList(2)).test()
        verify(omdbCacheDataStore).save(mutableListOf())
    }

    @Test
    fun saveOmdbsNeverCallsRemoteDataStore() {
        stubOmdbCacheSaveOmdbs(Completable.complete())
        omdbDataRepository.save(OmdbFactory.makeOmdbList(2)).test()
        verify(omdbRemoteDataStore, never()).save(mutableListOf())
    }
    //</editor-fold>

    //<editor-fold desc="Get Omdbs">
    @Test
    fun getOmdbsCompletes() {
        stubOmdbCacheDataStoreIsCached(Single.just(true))
        stubOmdbDataStoreFactoryRetrieveDataStore(omdbCacheDataStore)
        stubOmdbCacheDataStoreGetOmdbs(Flowable.just(OmdbFactory.makeOmdbResponseEntity()))
        stubOmdbCacheSaveOmdbs(Completable.complete())

        val testObserver = omdbDataRepository.get(title = null).test()
        testObserver.assertComplete()
    }

    @Test
    fun getOmdbsReturnsData() {
        stubOmdbCacheDataStoreIsCached(Single.just(true))
        stubOmdbDataStoreFactoryRetrieveDataStore(omdbCacheDataStore)
        stubOmdbCacheSaveOmdbs(Completable.complete())

        val omdbs = OmdbFactory.makeOmdbResponse()
        val omdbEntities = OmdbFactory.makeOmdbResponseEntity()

        stubOmdbMapperMapFromEntity(omdbEntities, omdbs)
        stubOmdbCacheDataStoreGetOmdbs(Flowable.just(omdbEntities))

        val testObserver = omdbDataRepository.get(title = null).test()
        testObserver.assertValue(omdbs)
    }

    @Test
    fun getOmdbsSavesOmdbsWhenFromCacheDataStore() {
        stubOmdbDataStoreFactoryRetrieveDataStore(omdbCacheDataStore)
        stubOmdbCacheSaveOmdbs(Completable.complete())
        omdbDataRepository.save(OmdbFactory.makeOmdbList(2)).test()
        verify(omdbCacheDataStore).save(mutableListOf())
    }

    @Test
    fun getOmdbsNeverSavesOmdbsWhenFromRemoteDataStore() {
        stubOmdbDataStoreFactoryRetrieveDataStore(omdbRemoteDataStore)
        stubOmdbCacheSaveOmdbs(Completable.complete())
        omdbDataRepository.save(OmdbFactory.makeOmdbList(2)).test()
        verify(omdbRemoteDataStore, never()).save(mutableListOf())
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubOmdbCacheSaveOmdbs(completable: Completable) {
        whenever(omdbCacheDataStore.save(mutableListOf()))
            .thenReturn(completable)
    }

    private fun stubOmdbCacheDataStoreIsCached(single: Single<Boolean>) {
        whenever(omdbCacheDataStore.isCached())
            .thenReturn(single)
    }

    private fun stubOmdbCacheDataStoreGetOmdbs(single: Flowable<ResponseEntity>) {
        whenever(omdbCacheDataStore.get(title = null))
            .thenReturn(single)
    }

    private fun stubOmdbRemoteDataStoreGetOmdbs(single: Flowable<ResponseEntity>) {
        whenever(omdbRemoteDataStore.get(title = null))
            .thenReturn(single)
    }

    private fun stubOmdbCacheClearOmdbs(completable: Completable) {
        whenever(omdbCacheDataStore.clear())
            .thenReturn(completable)
    }

    private fun stubOmdbDataStoreFactoryRetrieveCacheDataStore() {
        whenever(omdbDataStoreFactory.retrieveCacheDataStore())
            .thenReturn(omdbCacheDataStore)
    }

    private fun stubOmdbDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(omdbDataStoreFactory.retrieveRemoteDataStore())
            .thenReturn(omdbCacheDataStore)
    }

    private fun stubOmdbDataStoreFactoryRetrieveDataStore(dataStore: OmdbDataStore) {
        whenever(omdbDataStoreFactory.retrieveDataStore(any()))
            .thenReturn(dataStore)
    }

    private fun stubOmdbMapperMapFromEntity(
        omdbEntity: ResponseEntity,
        omdb: Response
    ) {
        whenever(responseMapper.mapFromEntity(omdbEntity))
            .thenReturn(omdb)
    }
    //</editor-fold>
}