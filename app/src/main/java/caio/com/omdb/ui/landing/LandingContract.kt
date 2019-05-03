package caio.com.omdb.ui.landing

import caio.com.domain.model.Response
import caio.com.omdb.Viewable

interface LandingContract {

    interface View : Viewable {
        fun onSuccess(data: Response)

        fun onFailure(error: String?)
    }

    interface Presenter {

    }
}