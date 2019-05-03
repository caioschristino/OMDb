package caio.com.domain.factory

import caio.com.domain.model.Omdb
import caio.com.domain.model.Response

class OmdbFactory {

    companion object Factory {

        fun makeOmdbList(count: Int): List<Omdb> {
            val bufferoos = mutableListOf<Omdb>()
            repeat(count) {
                bufferoos.add(makeOmdb())
            }
            return bufferoos
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