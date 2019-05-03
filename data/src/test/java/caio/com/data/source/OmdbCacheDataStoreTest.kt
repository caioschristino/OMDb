package caio.com.data.source

import caio.com.data.factory.OmdbFactory
import caio.com.data.model.OmdbEntity
import caio.com.data.repository.OmdbCache
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OmdbCacheDataStoreTest {
    private lateinit var omdbCacheDataStore: OmdbCacheDataStore
    private lateinit var omdbCache: OmdbCache

    @Before
    fun setUp() {
        omdbCache = mock()
        omdbCacheDataStore = OmdbCacheDataStore(omdbCache)
    }

    //<editor-fold desc="Clear Omdbs">
    @Test
    fun clearOmdbsCompletes() {
        stubOmdbCacheClearOmdbs(Completable.complete())
        val testObserver = omdbCacheDataStore.clear().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Save Omdbs">
    @Test
    fun saveOmdbsCompletes() {
        stubOmdbCacheSaveOmdbs(Completable.complete())
        val testObserver = omdbCacheDataStore.save(
            OmdbFactory.makeOmdbEntityList(2)
        ).test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Get Omdbs">
    @Test
    fun getOmdbsCompletes() {
        stubOmdbCacheGetOmdbs(Flowable.just(OmdbFactory.makeOmdbEntityList(2)))
        val testObserver = omdbCacheDataStore.get(title = null).test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubOmdbCacheSaveOmdbs(completable: Completable) {
        whenever(omdbCache.save(mutableListOf()))
            .thenReturn(completable)
    }

    private fun stubOmdbCacheGetOmdbs(single: Flowable<List<OmdbEntity>>) {
        whenever(omdbCache.get())
            .thenReturn(single)
    }

    private fun stubOmdbCacheClearOmdbs(completable: Completable) {
        whenever(omdbCache.clear())
            .thenReturn(completable)
    }
    //</editor-fold>

}