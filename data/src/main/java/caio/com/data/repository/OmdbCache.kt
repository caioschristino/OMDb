package caio.com.data.repository

import caio.com.data.model.OmdbEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Omdb. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface OmdbCache {

    /**
     * Clear all Omdb from the cache.
     */
    fun clear(): Completable

    /**
     * Save a given of Omdb to the cache.
     */
    fun save(item: OmdbEntity): Completable

    /**
     * Save a given list of Omdb to the cache.
     */
    fun save(item: List<OmdbEntity>): Completable

    /**
     * Retrieve a list of Omdb, from the cache.
     */
    fun get(): Flowable<List<OmdbEntity>>

    /**
     * Check whether there is a list of Omdb stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Single<Boolean>

    /**
     * Set a point in time at when the cache was last updated.
     *
     * @param lastCache the point in time at when the cache was last updated
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Check if the cache is expired.
     *
     * @return true if the cache is expired, otherwise false
     */
    fun isExpired(): Boolean
}