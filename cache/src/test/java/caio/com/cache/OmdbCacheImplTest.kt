package caio.com.cache

import android.arch.persistence.room.Room
import caio.com.cache.db.OmdbDatabase
import caio.com.cache.factory.OmdbFactory
import caio.com.cache.mapper.OmdbEntityMapper
import caio.com.cache.model.CachedOmdb
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(DefaultConfig.EMULATE_SDK))
class OmdbCacheImplTest {

    private var omdbDatabase = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application,
        OmdbDatabase::class.java
    ).allowMainThreadQueries().build()
    private var entityMapper = OmdbEntityMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)


    private val databaseHelper = OmdbCacheImpl(
        omdbDatabase,
        entityMapper, preferencesHelper
    )

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clear().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Omdbs">
    @Test
    fun saveOmdbsCompletes() {
        val omdbEntities = OmdbFactory.makeOmdbEntityList(2)

        val testObserver = databaseHelper.save(omdbEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveOmdbsSavesData() {
        val omdbCount = 2
        val omdbEntities = OmdbFactory.makeOmdbEntityList(omdbCount)

        databaseHelper.save(omdbEntities).test()
        checkNumRowsInOmdbsTable(omdbCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Omdbs">
    @Test
    fun getOmdbsCompletes() {
        val testObserver = databaseHelper.get().test()
        testObserver.assertComplete()
    }

    @Test
    fun getOmdbsReturnsData() {
        val omdbEntities = OmdbFactory.makeOmdbEntityList(2)
        val cachedOmdbs = mutableListOf<CachedOmdb>()
        omdbEntities.forEach {
            cachedOmdbs.add(entityMapper.mapToCached(it))
        }
        insertOmdbs(cachedOmdbs)

        val testObserver = databaseHelper.get().test()
        testObserver.assertValue(omdbEntities)
    }
    //</editor-fold>

    private fun insertOmdbs(cachedOmdbs: List<CachedOmdb>) {
        cachedOmdbs.forEach {
            omdbDatabase.cachedOmdbDao().insert(it)
        }
    }

    private fun checkNumRowsInOmdbsTable(expectedRows: Int) {
        val numberOfRows = omdbDatabase.cachedOmdbDao().get().size
        assertEquals(expectedRows, numberOfRows)
    }
}