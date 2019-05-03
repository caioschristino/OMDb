package caio.com.data.source

import caio.com.data.model.OmdbEntity
import caio.com.data.model.ResponseEntity
import caio.com.data.repository.OmdbDataStore
import caio.com.data.repository.OmdbRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [OmdbDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class OmdbRemoteDataStore @Inject constructor(private val remote: OmdbRemote) :
    OmdbDataStore {
    override fun save(item: List<OmdbEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun clear(): Completable {
        throw UnsupportedOperationException()
    }

    override fun save(item: OmdbEntity): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [OmdbEntity] instances from the API
     */
    override fun get(title: String?): Flowable<ResponseEntity> {
        return remote.get(title)
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}