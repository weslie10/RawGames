package com.weslie10.rawgames.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weslie10.rawgames.core.R
import com.weslie10.rawgames.core.databinding.ItemRowBinding
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.utils.Utility.setImage
import java.util.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {

    private var listData = ArrayList<Games>()
    var onItemClick: ((Games) -> Unit)? = null

    fun setData(newListData: List<Games>) {
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun getSwipedData(swipedPosition: Int): Games = listData[swipedPosition]

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
        fun bind(data: Games) {
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