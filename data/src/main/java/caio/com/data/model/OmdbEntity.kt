package caio.com.data.model

/**
 * Representation for a [OmdbEntity] fetched from an external layer data source
 */
data class OmdbEntity(
    var id: Int?,
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String
)