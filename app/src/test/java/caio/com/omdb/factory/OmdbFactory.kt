package caio.com.omdb.factory

import caio.com.data.factory.DataFactory
import caio.com.data.factory.OmdbFactory
import caio.com.domain.model.Omdb
import caio.com.domain.model.Response

class OmdbFactory {
    companion object Factory {

        fun makeOmdbResponse(): Response {
            return Response(
                OmdbFactory.makeOmdbList(5),
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