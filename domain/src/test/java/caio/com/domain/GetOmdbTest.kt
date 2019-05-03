package caio.com.domain

import caio.com.domain.factory.OmdbFactory
import caio.com.domain.model.Response
import caio.com.domain.repository.OmdbRepository
import caio.com.domain.usecase.GetOmdb
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetOmdbTest {
    private lateinit var getOmdb: GetOmdb

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockOmdbRepository: OmdbRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockOmdbRepository = mock()
        getOmdb = GetOmdb(
            mockOmdbRepository, mockThreadExecutor,
            mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getOmdb.buildUseCaseObservable(null)
        verify(mockOmdbRepository).get(title = null)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubOmdbRepositoryGetOmdb(Flowable.just(OmdbFactory.makeOmdbResponse()))
        val testObserver = getOmdb.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val omdb = OmdbFactory.makeOmdbResponse()
        stubOmdbRepositoryGetOmdb(Flowable.just(omdb))
        val testObserver = getOmdb.buildUseCaseObservable(null).test()
        testObserver.assertValue(omdb)
    }

    private fun stubOmdbRepositoryGetOmdb(single: Flowable<Response>) {
        whenever(mockOmdbRepository.get(title = null))
            .thenReturn(single)
    }
}