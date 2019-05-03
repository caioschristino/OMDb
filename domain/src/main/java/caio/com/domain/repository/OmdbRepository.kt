package caio.com.domain.repository

import caio.com.domain.model.Omdb
import caio.com.domain.model.Response
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface OmdbRepository {

    fun clear(): Completable

    fun save(item: List<Omdb>): Completable

    fun get(title: String?): Flowable<Response>
}