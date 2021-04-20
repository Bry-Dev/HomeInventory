package com.example.homeinventory.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeinventory.databinding.HomeItemListBinding
import com.example.homeinventory.model.HomeItem

class HomeRecyclerViewAdapter(private val onClickItem: OnClickItem) :  ListAdapter<HomeItem, HomeRecyclerViewAdapter.HomeRecyclerViewHolder>(HomeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return HomeRecyclerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, onClickItem)
    }

    class HomeRecyclerViewHolder(private val binding : HomeItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: HomeItem, onClickItem: OnClickItem) {
            binding.itemList = currentItem
            binding.listener = onClickItem
            binding.clickListener = HomeItemClickListener()
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): HomeRecyclerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeItemListBinding.inflate(layoutInflater, parent, false)
                return HomeRecyclerViewHolder(binding)
            }
        }
    }

    fun getHomeAt(position: Int): HomeItem? {
        return getItem(position)
    }


}

class HomeItemDiffCallback : DiffUtil.ItemCallback<HomeItem>() {
    override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.itemName == newItem.itemName && oldItem.itemQuantity == newItem.itemQuantity
    }
}

interface OnClickItem {
    fun onEditClick(homeItem: HomeItem)
    fun onLongEditClick(homeItem: HomeItem) : Boolean
}

class HomeItemClickListener {
    fun onClick( imgBtnEdit : ImageButton) {
        imgBtnEdit.visibility = if (imgBtnEdit.isShown) View.GONE else View.VISIBLE
    }
}

