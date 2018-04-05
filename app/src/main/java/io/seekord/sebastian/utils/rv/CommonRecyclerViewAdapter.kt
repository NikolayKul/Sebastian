package io.seekord.sebastian.utils.rv

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author NikolayKul
 */


/**
 *  RecyclerView.Adapter
 */
open class CommonAdapter<TBinding : ViewDataBinding, TViewItem : CommonViewItem<TBinding>> :
        RecyclerView.Adapter<CommonViewHolder<TBinding>>() {
    protected val items: MutableList<TViewItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder<TBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<TBinding>(inflater, viewType, parent, false)
        return CommonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommonViewHolder<TBinding>, position: Int) {
        items[position].bind(holder)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].layoutId

}


/**
 *  RecyclerView.ViewHolder
 */
open class CommonViewHolder<out TBinding : ViewDataBinding>(
        val binding: TBinding
) : RecyclerView.ViewHolder(binding.root)


/**
 *  ViewItem
 */
interface CommonViewItem<in TBinding : ViewDataBinding> {

    @get:LayoutRes
    val layoutId: Int

    fun bind(holder: CommonViewHolder<TBinding>)

}
