package caio.com.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import caio.com.cache.db.constants.OmdbConstants
import caio.com.cache.model.CachedOmdb

@Dao
abstract class CachedOmdbDao {

    @Query(OmdbConstants.GET_ALL)
    abstract fun get(): List<CachedOmdb>

    @Query(OmdbConstants.DELETE_ALL)
    abstract fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(omdb: CachedOmdb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(omdb: List<CachedOmdb>)
}