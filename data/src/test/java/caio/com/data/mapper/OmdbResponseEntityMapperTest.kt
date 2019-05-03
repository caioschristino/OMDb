package caio.com.data.mapper

import caio.com.data.factory.OmdbFactory
import caio.com.data.model.ResponseEntity
import caio.com.domain.model.Response
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class OmdbResponseEntityMapperTest {
    private lateinit var omdbResponseEntityMapper: OmdbResponseEntityMapper

    @Before
    fun setUp() {
        omdbResponseEntityMapper = OmdbResponseEntityMapper(OmdbEntityMapper())
    }

    @Test
    fun mapFromEntityMapsData() {
        val omdbEntity = OmdbFactory.makeOmdbResponseEntity()
        val omdb = omdbResponseEntityMapper.mapFromEntity(omdbEntity)

        assertResponseDataEquality(omdbEntity, omdb)
    }

    @Test
    fun mapToEntityMapsData() {
        val cachedOmdb = OmdbFactory.makeOmdbResponse()
        val omdbEntity = omdbResponseEntityMapper.mapToEntity(cachedOmdb)

        assertResponseDataEquality(omdbEntity, cachedOmdb)
    }

    private fun assertResponseDataEquality(
        responseEntity: ResponseEntity,
        response: Response
    ) {
        assertEquals(responseEntity.total, response.total)
        assertEquals(responseEntity.isResponse, response.isResponse)
        Assert.assertEquals(responseEntity.value, response.value)
    }
}