package caio.com.cache.mapper

import caio.com.cache.model.CachedOmdb
import caio.com.data.model.OmdbEntity
import javax.inject.Inject

/**
 * Map a [CachedOmdb] instance to and from a [OmdbEntity] instance when data is moving between
 * this later and the Data layer
 */
open class OmdbEntityMapper @Inject constructor() :
    EntityMapper<CachedOmdb, OmdbEntity> {

    /**
     * Map a [OmdbEntity] instance to a [CachedOmdb] instance
     */
    override fun mapToCached(map: OmdbEntity): CachedOmdb {
        return CachedOmdb(
            title = map.title,
            year = map.year,
            imdbID = map.imdbID,
            type = map.type,
            poster = map.poster
        )
    }

    /**
     * Map a [CachedOmdb] instance to a [OmdbEntity] instance
     */
    override fun mapFromCached(map: CachedOmdb): OmdbEntity {
        return OmdbEntity(map.id, map.title, map.year, map.imdbID, map.type, map.poster)
    }
}