package caio.com.omdb.widget

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


abstract class ViewAdapter<T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: MutableList<T> = mutableListOf()
    private var recoveryItems: MutableList<T> = mutableListOf()

    abstract fun setViewHolder(parent: ViewGroup, item: T): RecyclerView.ViewHolder

    abstract fun onBindData(holder: RecyclerView.ViewHolder, item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return setViewHolder(parent, items[viewType])
    }

    override fun getItemCount(): Int {
        var size = 0
        items?.let { size = it.size }
        return size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items?.get(position)?.let { onBindData(holder, it) }
    }

    fun filter(predicate: (T) -> Boolean): T {
        val filteredItems = items.filter(predicate)
        return filteredItems[0]
    }

    fun add(newItems: List<T>) {
        items?.let {
            if (it.isEmpty()) {
                it.addAll(newItems)
            } else {
                it.addAll(it.size, newItems)
            }
        }
        recoveryItems = items
        this.notifyDataSetChanged()
    }

    fun add(newItem: T) {
        items?.let {
            if (it.isEmpty()) {
                it.add(newItem)
            } else {
                it.add(it.size, newItem)
            }
        }
        this.notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
