package caio.com.omdb.ui.landing

import caio.com.domain.usecase.GetOmdb
import dagger.Module
import dagger.Provides


/**
 * Module used to provide dependencies at an ui-level.
 */
@Module
open class LandingModule {
    @Provides
    fun provideContactPresenter(
        get: GetOmdb
    ):
            LandingPresenter {
        return LandingPresenter(get)
    }
}
