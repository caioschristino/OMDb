package caio.com.remote.factory

import caio.com.remote.OmdbAPIService
import caio.com.remote.model.OmdbModel

class OmdbFactory {
    companion object Factory {

        fun makeOmdbResponse(): OmdbAPIService.APIResponse {
            var value = makeOmdbModelList(10)
            val OmdbResponse = OmdbAPIService.APIResponse(value, "10", true)
            return OmdbResponse
        }

        fun makeOmdbModelList(count: Int): List<OmdbModel> {
            val OmdbEntities = mutableListOf<OmdbModel>()
            repeat(count) {
                OmdbEntities.add(makeOmdbModel())
            }
            return OmdbEntities
        }

        fun makeOmdbModel(): OmdbModel {
            return OmdbModel(
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