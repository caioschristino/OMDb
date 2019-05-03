package caio.com.omdb.ui.landing

import caio.com.domain.model.Response
import caio.com.domain.usecase.GetOmdb
import caio.com.omdb.base.BasePresenter
import io.reactivex.subscribers.DisposableSubscriber

class LandingPresenter(var getcell: GetOmdb) :
    BasePresenter<LandingContract.View>(), LandingContract.Presenter {

    override fun onResume() {
        getcell.execute(GetSubscriber())
        super.onResume()
    }

    inner class GetSubscriber : DisposableSubscriber<Response>() {
        override fun onComplete() {}

        override fun onNext(data: Response) {
            getView()?.onSuccess(data)
        }

        override fun onError(exception: Throwable) {
            getView()?.onFailure(exception.message)
        }
    }
}