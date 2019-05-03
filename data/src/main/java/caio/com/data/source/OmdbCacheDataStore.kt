package caio.com.data.source

import caio.com.data.model.OmdbEntity
import caio.com.data.model.ResponseEntity
import caio.com.data.repository.OmdbCache
import caio.com.data.repository.OmdbDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [OmdbDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class OmdbCacheDataStore @Inject constructor(private val cache: OmdbCache) :
    OmdbDataStore {
    override fun save(item: List<OmdbEntity>): Completable {
        return cache.save(item)
            .doOnComplete {
                cache.setLastCacheTime(System.currentTimeMillis())
            }
    }

    /**
     * Clear all Omdbs from the cache
     */
    override fun clear(): Completable {
        return cache.clear()
    }

    /**
     * Save a given [List] of [OmdbEntity] instances to the cache
     */
    override fun save(item: OmdbEntity): Completable {
        return cache.save(item)
            .doOnComplete {
                cache.setLastCacheTime(System.currentTimeMillis())
            }
    }

    /**
     * Retrieve a list of [OmdbEntity] instance from the cache
     */
    override fun get(title: String?): Flowable<ResponseEntity> {
        return cache.get()
            .map {
                ResponseEntity(it, null, null)
            }
    }

    /**
     * Retrieve a list of [OmdbEntity] instance from the cache
     */
    override fun isCached(): Single<Boolean> {
        return cache.isCached()
    }
}