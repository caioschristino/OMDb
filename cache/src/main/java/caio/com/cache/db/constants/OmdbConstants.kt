package caio.com.cache.db.constants

/**
 * Defines constants for the Omdb Table
 */
object OmdbConstants {

    const val TABLE_NAME = "omdb_table"

    const val GET_ALL = "SELECT * FROM" + " " + TABLE_NAME

    const val DELETE_ALL = "DELETE FROM" + " " + TABLE_NAME
}