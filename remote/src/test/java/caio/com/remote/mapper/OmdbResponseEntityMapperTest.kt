package caio.com.remote.mapper

import caio.com.remote.factory.OmdbFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class OmdbResponseEntityMapperTest {
    private lateinit var omdbEntityMapper: OmdbEntityMapper
    private lateinit var omdbResponseEntityMapper: OmdbResponseEntityMapper

    @Before
    fun setUp() {
        omdbResponseEntityMapper = OmdbResponseEntityMapper(omdbEntityMapper)
    }

    @Test
    fun mapFromRemoteMapsData() {
        val omdbModel = OmdbFactory.makeOmdbResponse()
        val omdbEntity = omdbResponseEntityMapper.mapFromRemote(omdbModel)

        assertEquals(omdbModel.total, omdbEntity.total)
        assertEquals(omdbModel.isResponse, omdbEntity.isResponse)
        Assert.assertEquals(omdbModel.value, omdbEntity.value)
    }
}