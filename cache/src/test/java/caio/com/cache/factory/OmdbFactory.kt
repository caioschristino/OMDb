package caio.com.cache.factory

import caio.com.cache.model.CachedOmdb
import caio.com.data.model.OmdbEntity

class OmdbFactory {
    companion object Factory {

        fun makeCachedOmdb(): CachedOmdb {
            return CachedOmdb(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )
        }

        fun makeOmdbEntity(): OmdbEntity {
            return OmdbEntity(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )
        }

        fun makeOmdbEntityList(count: Int): List<OmdbEntity> {
            val omdbEntities = mutableListOf<OmdbEntity>()
            repeat(count) {
                omdbEntities.add(makeOmdbEntity())
            }
            return omdbEntities
        }

        fun makeCachedOmdbList(count: Int): List<CachedOmdb> {
            val cachedOmdbs = mutableListOf<CachedOmdb>()
            repeat(count) {
                cachedOmdbs.add(makeCachedOmdb())
            }
            return cachedOmdbs
        }

    }
}