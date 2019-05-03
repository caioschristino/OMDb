package caio.com.data.source

import caio.com.data.factory.OmdbFactory
import caio.com.data.model.ResponseEntity
import caio.com.data.repository.OmdbRemote
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OmdbRemoteDataStoreTest {
    private lateinit var omdbRemoteDataStore: OmdbRemoteDataStore
    private lateinit var omdbRemote: OmdbRemote

    @Before
    fun setUp() {
        omdbRemote = mock()
        omdbRemoteDataStore = OmdbRemoteDataStore(omdbRemote)
    }

    //<editor-fold desc="Clear Omdbs">
    @Test(expected = UnsupportedOperationException::class)
    fun clearOmdbsThrowsException() {
        omdbRemoteDataStore.clear().test()
    }
    //</editor-fold>

    //<editor-fold desc="Save Omdbs">
    @Test(expected = UnsupportedOperationException::class)
    fun saveOmdbsThrowsException() {
        omdbRemoteDataStore.save(OmdbFactory.makeOmdbEntityList(2)).test()
    }
    //</editor-fold>

    //<editor-fold desc="Get Omdbs">
    @Test
    fun getOmdbsCompletes() {
        stubOmdbCacheGetOmdbs(Flowable.just(OmdbFactory.makeOmdbResponseEntity()))
        val testObserver = omdbRemote.get(title = null).test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubOmdbCacheGetOmdbs(single: Flowable<ResponseEntity>) {
        whenever(omdbRemote.get(title = null))
            .thenReturn(single)
    }
    //</editor-fold>

}