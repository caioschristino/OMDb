package caio.com.data.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the cached model input type
 * @param <T> the remote model input type
 * @param <V> the model return type
 */
interface Mapper<E, D> {

    fun mapFromEntity(map: E): D

    fun mapToEntity(map: D): E

}