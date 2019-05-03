package caio.com.data.model

/**
 * Representation for a [ResponseEntity] fetched from an external layer data source
 */
data class ResponseEntity(val value: List<OmdbEntity>, var total: String?, var isResponse: Boolean?)