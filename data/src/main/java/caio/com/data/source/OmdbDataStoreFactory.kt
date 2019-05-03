package caio.com.data.source

import caio.com.data.repository.OmdbCache
import caio.com.data.repository.OmdbDataStore
import javax.inject.Inject

/**
 * Create an instance of a OmdbDataStore
 */
open class OmdbDataStoreFactory @Inject constructor(
    private val cache: OmdbCache,
    private val data: OmdbCacheDataStore,
    private val remote: OmdbRemoteDataStore
) {
    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): OmdbDataStore {
        if (isCached && !cache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): OmdbDataStore {
        return data
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): OmdbDataStore {
        return remote
    }
}