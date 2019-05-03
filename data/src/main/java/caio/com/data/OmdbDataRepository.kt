package caio.com.data

import caio.com.data.mapper.OmdbEntityMapper
import caio.com.data.mapper.OmdbResponseEntityMapper
import caio.com.data.source.OmdbDataStoreFactory
import caio.com.domain.model.Omdb
import caio.com.domain.model.Response
import caio.com.domain.repository.OmdbRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Provides an implementation of the [OmdbRepository] interface for communicating to and from
 * data sources
 */
class OmdbDataRepository @Inject constructor(
    private val factory: OmdbDataStoreFactory,
    private val mapper: OmdbEntityMapper,
    private val mapperResponse: OmdbResponseEntityMapper
) :
    OmdbRepository {

    override fun clear(): Completable {
        return factory.retrieveCacheDataStore().clear()
    }

    override fun save(item: List<Omdb>): Completable {
        return factory.retrieveCacheDataStore().save(item.map { mapper.mapToEntity(it) })
    }

    override fun get(title: String?): Flowable<Response> {
        return factory.retrieveCacheDataStore().isCached()
            .flatMapPublisher {
                factory.retrieveDataStore(it).get(title)
            }
            .flatMap {
                Flowable.just(mapperResponse.mapFromEntity(it))
            }
            .flatMap {
                save(it.value).toSingle { it }.toFlowable()
            }
    }
}