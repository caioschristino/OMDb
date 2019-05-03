package caio.com.omdb.ui

import android.support.v4.app.FragmentTransaction
import caio.com.omdb.R
import caio.com.omdb.ui.landing.LandingFragment

interface IMainNavigator {
    fun goToDetail()

    fun closeDetail(): Boolean

    fun goToLanding()
}

class MainNavigator(private var mainActivity: MainActivity) : IMainNavigator {

    override fun goToDetail() {
        val fragment = LandingFragment.newInstance()
        mainActivity.supportFragmentManager.beginTransaction().add(R.id.fragment_frame, fragment, LandingFragment.TAG)
            .commitNow()
    }

    override fun closeDetail(): Boolean {
        val details = mainActivity.supportFragmentManager.findFragmentByTag(LandingFragment.TAG)
        if (details != null) {
            mainActivity.supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(details)
                .commitNow()
            return true
        }
        return false
    }


    override fun goToLanding() {
        val fragment = LandingFragment.newInstance()

        val transaction = mainActivity.supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(R.id.fragment_frame, fragment).commitNow()
    }
}