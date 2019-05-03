package caio.com.cache.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the cached model input type
 * @param <T> the remote model input type
 * @param <V> the model return type
 */
interface EntityMapper<T, V> {

    fun mapFromCached(map: T): V

    fun mapToCached(map: V): T
}