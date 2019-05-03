package caio.com.cache

import caio.com.cache.db.OmdbDatabase
import caio.com.cache.mapper.OmdbEntityMapper
import caio.com.data.model.OmdbEntity
import caio.com.data.repository.OmdbCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Omdb instances. This class implements the
 * [OmdbCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class OmdbCacheImpl @Inject constructor(
    val OmdbsDatabase: OmdbDatabase,
    private val mapper: OmdbEntityMapper,
    private val preferences: PreferencesHelper
) :
    OmdbCache {
    override fun save(item: OmdbEntity): Completable {
        return Completable.defer {
            OmdbsDatabase.cachedOmdbDao().insert(mapper.mapToCached(item))
            Completable.complete()
        }
    }

    private val EXPIRATION_TIME = (0).toLong()

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clear(): Completable {
        return Completable.defer {
            OmdbsDatabase.cachedOmdbDao().clear()
            Completable.complete()
        }
    }

    /**
     * Save the given list of [OmdbEntity] instances to the database.
     */
    override fun save(item: List<OmdbEntity>): Completable {
        return Completable.defer {
            OmdbsDatabase.cachedOmdbDao().insert(item.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [OmdbEntity] instances from the database.
     */
    override fun get(): Flowable<List<OmdbEntity>> {
        return Flowable.defer {
            Flowable.just(OmdbsDatabase.cachedOmdbDao().get())
        }.map {
            it.map { mapper.mapFromCached(it) }
        }
    }

    /**
     * Check whether there are instances of [CachedOmdb] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(OmdbsDatabase.cachedOmdbDao().get().isNotEmpty())
        }
    }

    /**
     * Set a point in time at when the cache was last updated.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferences.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferences.lastCacheTime
    }
}