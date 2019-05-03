package caio.com.omdb

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import caio.com.omdb.injection.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class OMDbApp : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun activityInjector() = activityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}
