package caio.com.cache.mapper

import caio.com.cache.factory.OmdbFactory
import caio.com.cache.model.CachedOmdb
import caio.com.data.model.OmdbEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class OmdbEntityMapperTest {

    private lateinit var omdbEntityMapper: OmdbEntityMapper

    @Before
    fun setUp() {
        omdbEntityMapper = OmdbEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val omdbEntity = OmdbFactory.makeOmdbEntity()
        val cachedOmdb = omdbEntityMapper.mapToCached(omdbEntity)

        assertOmdbDataEquality(omdbEntity, cachedOmdb)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedOmdb = OmdbFactory.makeCachedOmdb()
        val omdbEntity = omdbEntityMapper.mapFromCached(cachedOmdb)

        assertOmdbDataEquality(omdbEntity, cachedOmdb)
    }

    private fun assertOmdbDataEquality(
        omdbEntity: OmdbEntity,
        cachedOmdb: CachedOmdb
    ) {
        assertEquals(omdbEntity.title, cachedOmdb.title)
        assertEquals(omdbEntity.year, cachedOmdb.year)
        assertEquals(omdbEntity.imdbID, cachedOmdb.imdbID)
        assertEquals(omdbEntity.type, cachedOmdb.type)
        assertEquals(omdbEntity.poster, cachedOmdb.poster)
    }
}