package caio.com.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import caio.com.cache.dao.CachedOmdbDao
import caio.com.cache.model.CachedOmdb
import javax.inject.Inject

@Database(entities = arrayOf(CachedOmdb::class), version = 1)
abstract class OmdbDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedOmdbDao(): CachedOmdbDao
}