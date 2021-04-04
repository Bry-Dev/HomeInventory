package com.example.homeinventory.ui.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeinventory.databinding.ShoppingListBinding
import com.example.homeinventory.model.ShoppingForHome

class ShoppingRecyclerViewAdapter :  ListAdapter<ShoppingForHome, ShoppingRecyclerViewAdapter.ShoppingViewHolder>(
    ShoppingForHomeDiffCallback()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class ShoppingViewHolder(private val binding: ShoppingListBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(currentItem: ShoppingForHome) {
            binding.shoppingItems = currentItem
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ShoppingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ShoppingListBinding.inflate(layoutInflater, parent, false)
                return ShoppingViewHolder(binding)
            }
        }
    }

    fun getShoppingAt(position: Int): ShoppingForHome? {
        return getItem(position)
    }
}

class ShoppingForHomeDiffCallback : DiffUtil.ItemCallback<ShoppingForHome>() {
    override fun areItemsTheSame(oldItem: ShoppingForHome, newItem: ShoppingForHome): Boolean {
        return oldItem.shoppingItem.itemId == newItem.shoppingItem.itemId
    }

    override fun areContentsTheSame(oldItem: ShoppingForHome, newItem: ShoppingForHome): Boolean {
        return oldItem == newItem
    }
}


