package caio.com.omdb.injection.module

import caio.com.omdb.ui.landing.LandingFragment
import caio.com.omdb.ui.landing.LandingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(LandingModule::class))
    abstract fun bindContactFragment(): LandingFragment
}