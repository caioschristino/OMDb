package caio.com.data.repository

import caio.com.data.model.OmdbEntity
import caio.com.data.model.ResponseEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Omdb.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface OmdbDataStore {
    fun clear(): Completable

    fun save(item: OmdbEntity): Completable

    fun save(item: List<OmdbEntity>): Completable

    fun get(title: String?): Flowable<ResponseEntity>

    fun isCached(): Single<Boolean>
}