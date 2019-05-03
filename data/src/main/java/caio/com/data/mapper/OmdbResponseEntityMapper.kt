package caio.com.data.mapper

import caio.com.data.model.ResponseEntity
import caio.com.domain.model.Response
import javax.inject.Inject

/**
 * Map a [ResponseEntity] to and from a [Response] instance when data is moving between
 * this later and the Domain layer
 */
open class OmdbResponseEntityMapper @Inject constructor(private val mapper: OmdbEntityMapper) :
    Mapper<ResponseEntity, Response> {

    /**
     * Map a [ResponseEntity] instance to a [Response] instance
     */
    override fun mapFromEntity(map: ResponseEntity): Response {
        var value = map.value.map { mapper.mapFromEntity(it) }
        return Response(value, map.total, map.isResponse)
    }

    /**
     * Map a [Response] instance to a [ResponseEntity] instance
     */
    override fun mapToEntity(map: Response): ResponseEntity {
        var value = map.value.map { mapper.mapToEntity(it) }
        return ResponseEntity(value, map.total, map.isResponse)
    }
}