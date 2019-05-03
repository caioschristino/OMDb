package caio.com.omdb.widget.viewholder

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import caio.com.omdb.R

class OmdbViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    val poster: ImageView
    val liked: ImageView
    var title: TextView
    var year: TextView
    var imdbID: TextView
    var type: TextView

    init {
        poster = view.findViewById(R.id.poster)
        liked = view.findViewById(R.id.liked)
        title = view.findViewById(R.id.title)
        year = view.findViewById(R.id.year)
        imdbID = view.findViewById(R.id.imdbID)
        type = view.findViewById(R.id.type)
    }
}
