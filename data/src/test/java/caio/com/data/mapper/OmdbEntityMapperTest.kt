package caio.com.data.mapper

import caio.com.data.factory.OmdbFactory
import caio.com.data.model.OmdbEntity
import caio.com.domain.model.Omdb
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class OmdbEntityMapperTest {
    private lateinit var omdbMapper: OmdbEntityMapper

    @Before
    fun setUp() {
        omdbMapper = OmdbEntityMapper()
    }

    @Test
    fun mapFromEntityMapsData() {
        val OmdbEntity = OmdbFactory.makeOmdbEntity()
        val Omdb = omdbMapper.mapFromEntity(OmdbEntity)

        assertOmdbDataEquality(OmdbEntity, Omdb)
    }

    @Test
    fun mapToEntityMapsData() {
        val cachedOmdb = OmdbFactory.makeOmdb()
        val OmdbEntity = omdbMapper.mapToEntity(cachedOmdb)

        assertOmdbDataEquality(OmdbEntity, cachedOmdb)
    }

    private fun assertOmdbDataEquality(
        omdbEntity: OmdbEntity,
        omdb: Omdb
    ) {
        assertEquals(omdbEntity.title, omdb.title)
        assertEquals(omdbEntity.year, omdb.year)
        assertEquals(omdbEntity.imdbID, omdb.imdbID)
        assertEquals(omdbEntity.type, omdb.type)
        assertEquals(omdbEntity.poster, omdb.poster)
    }
}