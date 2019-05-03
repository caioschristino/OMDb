package caio.com.data.factory

import caio.com.data.model.OmdbEntity
import caio.com.data.model.ResponseEntity
import caio.com.domain.model.Omdb
import caio.com.domain.model.Response

class OmdbFactory {
    companion object Factory {
        fun makeOmdbEntityList(count: Int): List<OmdbEntity> {
            val OmdbEntities = mutableListOf<OmdbEntity>()
            repeat(count) {
                OmdbEntities.add(makeOmdbEntity())
            }
            return OmdbEntities
        }

        fun makeOmdbList(count: Int): List<Omdb> {
            val omdb = mutableListOf<Omdb>()
            repeat(count) {
                omdb.add(makeOmdb())
            }
            return omdb
        }

        fun makeOmdbResponseEntity(): ResponseEntity {
            return ResponseEntity(
                makeOmdbEntityList(5),
                DataFactory.randomUuid(),
                DataFactory.randomBoolean()
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

        fun makeOmdbResponse(): Response {
            return Response(
                makeOmdbList(5),
                DataFactory.randomUuid(),
                DataFactory.randomBoolean()
            )
        }

        fun makeOmdb(): Omdb {
            return Omdb(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )
        }
    }
}