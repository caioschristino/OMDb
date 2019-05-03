package caio.com.omdb.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import caio.com.omdb.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        var mainNavigator = MainNavigator(this)
        mainNavigator.goToLanding()
    }
}
