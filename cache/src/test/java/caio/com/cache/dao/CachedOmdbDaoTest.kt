package caio.com.cache.dao

import android.arch.persistence.room.Room
import caio.com.cache.db.OmdbDatabase
import caio.com.cache.factory.OmdbFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
open class CachedOmdbDaoTest {

    private lateinit var omdbDatabase: OmdbDatabase

    @Before
    fun initDb() {
        omdbDatabase = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.baseContext,
            OmdbDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDb() {
        omdbDatabase.close()
    }

    @Test
    fun insertOmdbsSavesData() {
        val cachedOmdb = OmdbFactory.makeCachedOmdb()
        omdbDatabase.cachedOmdbDao().insert(cachedOmdb)

        val omdb = omdbDatabase.cachedOmdbDao().get()
        assert(omdb.isNotEmpty())
    }

    @Test
    fun getOmdbsRetrievesData() {
        val cachedOmdbs = OmdbFactory.makeCachedOmdbList(5)

        cachedOmdbs.forEach {
            omdbDatabase.cachedOmdbDao().insert(it)
        }

        val retrievedOmdbs = omdbDatabase.cachedOmdbDao().get()
        assert(retrievedOmdbs == cachedOmdbs.sortedWith(compareBy({ it.id }, { it.id })))
    }

}