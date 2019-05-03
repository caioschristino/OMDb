package caio.com.remote.mapper

import caio.com.remote.factory.OmdbFactory
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
    fun mapFromRemoteMapsData() {
        val omdbModel = OmdbFactory.makeOmdbModel()
        val omdbEntity = omdbEntityMapper.mapFromRemote(omdbModel)

        assertEquals(omdbModel.title, omdbEntity.title)
        assertEquals(omdbModel.year, omdbEntity.year)
        assertEquals(omdbModel.imdbID, omdbEntity.imdbID)
        assertEquals(omdbModel.type, omdbEntity.type)
        assertEquals(omdbModel.poster, omdbEntity.poster)
    }
}