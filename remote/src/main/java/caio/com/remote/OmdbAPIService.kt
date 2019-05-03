package caio.com.remote

import caio.com.remote.model.OmdbModel
import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Defines the abstract methods used for interacting with the OmdbAPI
 */
interface OmdbAPIService {
    @GET("/")
    fun get(
        @Query("apikey") key: String = BuildConfig.API_KEY,
        @Query("s") s: String?
    ): Flowable<APIResponse>

    @GET
    fun detail(
        @Query("apikey") apikey: String,
        @Query("i") i: String
    ): Flowable<APIResponse>

    class APIResponse(
        @SerializedName("Search")
        var value: List<OmdbModel>,
        @SerializedName("totalResults")
        var total: String,
        @SerializedName("Response")
        var isResponse: Boolean
    )
}