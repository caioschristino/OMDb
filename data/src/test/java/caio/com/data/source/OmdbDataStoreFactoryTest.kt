package caio.com.data.source

import caio.com.data.repository.OmdbCache
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OmdbDataStoreFactoryTest {
    private lateinit var omdbDataStoreFactory: OmdbDataStoreFactory
    private lateinit var omdbCache: OmdbCache
    private lateinit var omdbCacheDataStore: OmdbCacheDataStore
    private lateinit var omdbRemoteDataStore: OmdbRemoteDataStore

    @Before
    fun setUp() {
        omdbCache = mock()
        omdbCacheDataStore = mock()
        omdbRemoteDataStore = mock()
        omdbDataStoreFactory = OmdbDataStoreFactory(
            omdbCache,
            omdbCacheDataStore, omdbRemoteDataStore
        )
    }

    //<editor-fold desc="Retrieve Data Store">
    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        stubOmdbCacheIsCached(Single.just(false))
        val omdbDataStore = omdbDataStoreFactory.retrieveDataStore(false)
        assert(omdbDataStore is OmdbRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenCacheExpiredReturnsRemoteDataStore() {
        stubOmdbCacheIsCached(Single.just(true))
        stubOmdbCacheIsExpired(true)
        val omdbDataStore = omdbDataStoreFactory.retrieveDataStore(true)
        assert(omdbDataStore is OmdbRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreReturnsCacheDataStore() {
        stubOmdbCacheIsCached(Single.just(true))
        stubOmdbCacheIsExpired(false)
        val omdbDataStore = omdbDataStoreFactory.retrieveDataStore(true)
        assert(omdbDataStore is OmdbCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Remote Data Store">
    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        val omdbDataStore = omdbDataStoreFactory.retrieveRemoteDataStore()
        assert(omdbDataStore is OmdbRemoteDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Cache Data Store">
    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        val omdbDataStore = omdbDataStoreFactory.retrieveCacheDataStore()
        assert(omdbDataStore is OmdbCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubOmdbCacheIsCached(single: Single<Boolean>) {
        whenever(omdbCache.isCached())
            .thenReturn(single)
    }

    private fun stubOmdbCacheIsExpired(isExpired: Boolean) {
        whenever(omdbCache.isExpired())
            .thenReturn(isExpired)
    }
    //</editor-fold>

}