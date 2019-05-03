package caio.com.omdb.ui.landing

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import caio.com.domain.model.Omdb
import caio.com.domain.model.Response
import caio.com.omdb.R
import caio.com.omdb.base.BaseFragment
import caio.com.omdb.widget.GlideApp
import caio.com.omdb.widget.ViewAdapter
import caio.com.omdb.widget.viewholder.OmdbViewHolder
import kotlinx.android.synthetic.main.fragment_landing.*
import javax.inject.Inject

class LandingFragment : BaseFragment<LandingPresenter>(), LandingContract.View {
    override fun onSuccess(data: Response) {
        adapter.add(data.value)
    }

    override fun onFailure(error: String?) {

    }

    override val layoutId: Int
        get() = R.layout.fragment_landing

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getView()?.isFocusableInTouchMode = true
        getView()?.requestFocus()

        recycler_view.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = adapter
    }

    @Inject
    override fun injectPresenter(presenter: LandingPresenter) {
        super.injectPresenter(presenter)
    }

    companion object {
        const val TAG = "LandingFragment"

        fun newInstance(): LandingFragment {
            return LandingFragment()
        }
    }

    private var adapter = object : ViewAdapter<Omdb>() {
        override fun setViewHolder(parent: ViewGroup, item: Omdb): RecyclerView.ViewHolder {
            val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.omdb, parent, false)
            return OmdbViewHolder(itemView)
        }

        override fun onBindData(holder: RecyclerView.ViewHolder, item: Omdb) {
            if (holder is OmdbViewHolder) {
                GlideApp.with(holder.view.context)
                    .asBitmap()
                    .load(item.poster)
                    .into(holder.poster)

                holder.imdbID.text = "ID: ${item.imdbID}"
                holder.title.text = item.title
                holder.year.text = "Ano (${item.year})"
                holder.type.text = "Tipo: ${item.type}"
            }
        }
    }
}
