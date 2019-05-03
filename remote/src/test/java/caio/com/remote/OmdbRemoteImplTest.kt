package caio.com.remote

import caio.com.remote.factory.OmdbFactory
import caio.com.remote.mapper.OmdbResponseEntityMapper
import caio.com.remote.source.OmdbRemoteImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OmdbRemoteImplTest {

    private lateinit var entityMapper: OmdbResponseEntityMapper
    private lateinit var omdbService: OmdbAPIService
    private lateinit var omdbRemoteImpl: OmdbRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        omdbService = mock()
        omdbRemoteImpl = OmdbRemoteImpl(omdbService, entityMapper)
    }

    //<editor-fold desc="Get Omdb">
    @Test
    fun getOmdbCompletes() {
        stubOmdbServiceGetOmdbs(Flowable.just(OmdbFactory.makeOmdbResponse()))
        val testObserver = omdbService.get(s = null).test()
        testObserver.assertComplete()
    }

    @Test
    fun getOmdbReturnsData() {
        val omdbResponse = OmdbFactory.makeOmdbResponse()
        var responseEntity = entityMapper.mapFromRemote(omdbResponse)

        stubOmdbServiceGetOmdbs(Flowable.just(omdbResponse))
        val testObserver = omdbRemoteImpl.get(title = null).test()
        testObserver.assertValue(responseEntity)
    }
    //</editor-fold>

    private fun stubOmdbServiceGetOmdbs(
        observable:
        Flowable<OmdbAPIService.APIResponse>
    ) {
        whenever(omdbService.get(s = null))
            .thenReturn(observable)
    }
}