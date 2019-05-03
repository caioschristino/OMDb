package caio.com.domain.model

/**
 * Representation for a [Response] fetched from an external layer data source
 */
data class Response(val value: List<Omdb>, var total: String?, var isResponse: Boolean?)