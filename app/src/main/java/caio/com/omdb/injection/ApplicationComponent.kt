package caio.com.omdb.injection

import android.app.Application
import caio.com.omdb.OMDbApp
import caio.com.omdb.injection.module.ActivityBindingModule
import caio.com.omdb.injection.module.ApplicationModule
import caio.com.omdb.injection.module.FragmentBindingModule
import caio.com.omdb.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = arrayOf(
        FragmentBindingModule::class,
        ActivityBindingModule::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class
    )
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: OMDbApp)
}
