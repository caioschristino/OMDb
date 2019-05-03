package caio.com.remote.mapper

import caio.com.data.model.OmdbEntity
import caio.com.data.model.ResponseEntity
import caio.com.remote.OmdbAPIService
import javax.inject.Inject

/**
 * Map a [OmdbResponse] to and from a [ResponseEntity] instance when data is moving between
 * this later and the Data layer
 */
open class OmdbResponseEntityMapper @Inject constructor(private val mapper: OmdbEntityMapper) :
    EntityMapper<OmdbAPIService.APIResponse, ResponseEntity> {

    /**
     * Map an instance of a [OmdbResponse] to a [OmdbEntity] model
     */
    override fun mapFromRemote(map: OmdbAPIService.APIResponse): ResponseEntity {
        var value = map.value.map { mapper.mapFromRemote(it) }
        return ResponseEntity(value, map.total, map.isResponse)
    }
}