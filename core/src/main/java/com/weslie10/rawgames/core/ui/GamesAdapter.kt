package com.weslie10.rawgames.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weslie10.rawgames.core.R
import com.weslie10.rawgames.core.data.source.remote.response.ResultsItem
import com.weslie10.rawgames.core.databinding.ItemRowBinding
import com.weslie10.rawgames.core.utils.Utility.setImage
import java.util.*

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.ListViewHolder>() {

    private var listData = ArrayList<ResultsItem>()
    var onItemClick: ((ResultsItem) -> Unit)? = null

    fun setData(newListData: List<ResultsItem>) {
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowBinding.bind(itemView)
        fun bind(data: ResultsItem) {
            with(binding) {
                itemImage.setImage(data.backgroundImage)
                itemName.text = data.name
                itemDate.text = data.released
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}