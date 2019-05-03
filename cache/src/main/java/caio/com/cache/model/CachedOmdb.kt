package caio.com.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import caio.com.cache.db.constants.OmdbConstants

/**
 * Model used solely for the caching of a Omdb
 */
@Entity(tableName = OmdbConstants.TABLE_NAME)
data class CachedOmdb(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String
)
