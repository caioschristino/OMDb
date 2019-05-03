package caio.com.domain.model

/**
 * Representation for a [Omdb] fetched from an external layer data source
 */
data class Omdb(
    var id: Int?,
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String
)