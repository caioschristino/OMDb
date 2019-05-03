package caio.com.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Representation for a [OmdbModel] fetched from the API
 */
class OmdbModel(
    var id: Int?,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Poster")
    val poster: String
)