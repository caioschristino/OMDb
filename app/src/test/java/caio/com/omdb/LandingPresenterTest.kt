package caio.com.omdb

import caio.com.domain.usecase.GetOmdb
import caio.com.omdb.ui.landing.LandingContract
import caio.com.omdb.ui.landing.LandingPresenter
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class LandingPresenterTest {
    @Mock
    lateinit var view: LandingContract.View

    @Mock
    lateinit var getOmdb: GetOmdb
    private lateinit var presenter: LandingPresenter

    @Before
    fun setUp() {
        getOmdb = mock()
        presenter = LandingPresenter(getOmdb)
        presenter.attachView(view)
    }

    @Test
    fun getOmdbExecutesOnSuccess() {
        verify(getOmdb, times(1)).execute(any(), anyOrNull())

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).onSuccess(any())
    }

    @Test
    fun getOmdbExecutesOnFailure() {
        presenter.onResume()
        verify(getOmdb, times(1)).execute(any(), anyOrNull())

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).onFailure(any())
    }
}