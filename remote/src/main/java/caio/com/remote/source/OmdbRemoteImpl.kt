package caio.com.remote.source

import caio.com.data.model.OmdbEntity
import caio.com.data.model.ResponseEntity
import caio.com.data.repository.OmdbRemote
import caio.com.remote.OmdbAPIService
import caio.com.remote.mapper.OmdbResponseEntityMapper
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [OmdbService] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class OmdbRemoteImpl @Inject constructor(
    private val service: OmdbAPIService,
    private val mapper: OmdbResponseEntityMapper
) :
    OmdbRemote {
    /**
     * Retrieve a list of [OmdbEntity] instances from the [OmdbService].
     */
    override fun get(title: String?): Flowable<ResponseEntity> {
        var value = title?.let { it } ?: "all"

        return service.get(s = value)
            .map {
                mapper.mapFromRemote(it)
            }
    }
}