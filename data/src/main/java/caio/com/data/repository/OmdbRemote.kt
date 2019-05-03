package caio.com.data.repository

import caio.com.data.model.ResponseEntity
import io.reactivex.Flowable

/**
 * Interface defining methods for the caching of Omdbs. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface OmdbRemote {

    /**
     * Retrieve a list of Omdb, from the cache
     */
    fun get(title: String?): Flowable<ResponseEntity>
}