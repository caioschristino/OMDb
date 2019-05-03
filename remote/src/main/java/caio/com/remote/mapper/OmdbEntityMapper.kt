package caio.com.remote.mapper

import caio.com.data.model.OmdbEntity
import caio.com.remote.model.OmdbModel
import javax.inject.Inject

/**
 * Map a [OmdbModel] to and from a [OmdbEntity] instance when data is moving between
 * this later and the Data layer
 */
open class OmdbEntityMapper @Inject constructor() :
    EntityMapper<OmdbModel, OmdbEntity> {

    /**
     * Map an instance of a [OmdbModel] to a [OmdbEntity] model
     */
    override fun mapFromRemote(map: OmdbModel): OmdbEntity {
        return OmdbEntity(map.id, map.title, map.year, map.imdbID, map.type, map.poster)
    }
}