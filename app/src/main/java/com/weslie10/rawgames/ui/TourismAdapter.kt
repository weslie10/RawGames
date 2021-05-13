package com.weslie10.rawgames.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weslie10.rawgames.R
import com.weslie10.rawgames.core.domain.model.Games
import java.util.*

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.ListViewHolder>() {

    private var listData = ArrayList<Games>()
    var onItemClick: ((Games) -> Unit)? = null

    fun setData(newListData: List<Games>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_Games, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListGamesBinding.bind(itemView)
        fun bind(data: Games) {
//            with(binding) {
//                Glide.with(itemView.context)
//                    .load(data.image)
//                    .into(ivItemImage)
//                tvItemTitle.text = data.name
//                tvItemSubtitle.text = data.address
//            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}