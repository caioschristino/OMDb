package caio.com.omdb.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import caio.com.omdb.Viewable
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : BasePresenter<Viewable>> : Fragment(),
    Viewable {

    /**
     * {@inheritDoc}
     */
    private var presenter: T? = null

    protected abstract val layoutId: Int

    protected val appCompatActivity: AppCompatActivity
        get() = activity as AppCompatActivity

    /**
     * {@inheritDoc}
     */
    override fun setTitle(@StringRes resource: Int) {
        setTitle(getString(resource))
    }


    fun getPresenter(): T? {
        return presenter
    }

    /**
     * {@inheritDoc}
     */
    override fun setTitle(msg: CharSequence) {
        activity?.title = msg
    }

    /**
     * {@inheritDoc}
     */
    override fun onStart() {
        super.onStart()
        if (presenter != null) {
            presenter!!.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        if (presenter != null) {
            presenter!!.onResume()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        retainInstance = true
        super.onAttach(context)
    }

    /**
     * {@inheritDoc}
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        injectDependencies()

        if (presenter != null) {
            presenter?.attachView(this)
        }

        return view
    }

    /**
     * {@inheritDoc}
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (presenter != null) {
            presenter!!.onViewCreated()
        }
    }

    /**
     * {@inheritDoc}
     */
    override fun onDestroyView() {
        if (presenter != null) {
            presenter!!.detachView()
        }
        super.onDestroyView()
    }

    /**
     * {@inheritDoc}
     */
    override fun onStop() {
        if (presenter != null) {
            presenter!!.onStop()
        }
        super.onStop()
    }

    /**
     * {@inheritDoc}
     */
    override fun onDestroy() {
        presenter = null
        super.onDestroy()
    }

    /**
     * {@inheritDoc}
     */
    override fun displayError(message: String) {
        val rootContent = view?.findViewById<View>(android.R.id.content)
        if (rootContent != null) {
            Snackbar.make(rootContent, message, Snackbar.LENGTH_LONG).show()
        }
    }


    /**
     * {@inheritDoc}
     */
    override fun displayError(messageId: Int) {
        displayError(getString(messageId))
    }

    /**
     * {@inheritDoc}
     */
    override fun showLoading() {
        // no-op by default
    }

    /**
     * {@inheritDoc}
     */
    override fun hideLoading() {
        // no-op by default
    }

    override fun injectDependencies() {

    }

    override fun attachToPresenter() {

    }

    override fun detachFromPresenter() {

    }

    override fun onLandscape() {

    }

    override fun onPortrait() {

    }

    override fun displayMessage(message: String) {

    }

    override fun showNoNetwork() {

    }

    override fun close() {
        appCompatActivity.finish()
    }

    open fun injectPresenter(presenter: T) {
        this.presenter = presenter
    }

    interface FragmentNavigator {
        fun changeFragment(
            fragmentTag: String,
            sharedElement: Pair<View, String>?
        )
    }
}