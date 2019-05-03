package caio.com.data.mapper

import caio.com.data.model.OmdbEntity
import caio.com.domain.model.Omdb
import javax.inject.Inject


/**
 * Map a [OmdbEntity] to and from a [Omdb] instance when data is moving between
 * this later and the Domain layer
 */
open class OmdbEntityMapper @Inject constructor() : Mapper<OmdbEntity, Omdb> {

    /**
     * Map a [OmdbEntity] instance to a [Omdb] instance
     */
    override fun mapFromEntity(map: OmdbEntity): Omdb {
        return Omdb(map.id, map.title, map.year, map.imdbID, map.type, map.poster)
    }

    /**
     * Map a [Omdb] instance to a [OmdbEntity] instance
     */
    override fun mapToEntity(map: Omdb): OmdbEntity {
        return OmdbEntity(map.id, map.title, map.year, map.imdbID, map.type, map.poster)
    }
}