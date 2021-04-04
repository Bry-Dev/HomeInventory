package com.example.homeinventory.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeinventory.R
import com.example.homeinventory.databinding.CategoryListBinding
import com.example.homeinventory.model.CategoryWithItems

class CategoryRecyclerViewAdapter(private val clickListener: CategoryClickListener) :
    ListAdapter<CategoryWithItems, CategoryRecyclerViewAdapter.CategoryRecyclerViewHolder>(CategoryDiffCallback()) {
    private val viewPool = RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecyclerViewHolder {
        return CategoryRecyclerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryRecyclerViewHolder, position: Int) {
        val currentItem = getItem(position)
        val clickListener = clickListener
        holder.bind(currentItem, clickListener, viewPool)
    }

    class CategoryRecyclerViewHolder(private val binding: CategoryListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: CategoryWithItems, clickListener: CategoryClickListener, viewPool : RecyclerView.RecycledViewPool) {
            binding.homeItems = currentItem
            binding.recyclerHomeItem.setRecycledViewPool(viewPool)
            binding.homeClickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CategoryRecyclerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategoryListBinding.inflate(layoutInflater, parent, false)
                return CategoryRecyclerViewHolder(binding)
            }
        }
    }
}

class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryWithItems>() {
    override fun areItemsTheSame(oldItem: CategoryWithItems, newItem: CategoryWithItems): Boolean {
        return oldItem.category.categoryId == newItem.category.categoryId
    }

    override fun areContentsTheSame(oldItem: CategoryWithItems, newItem: CategoryWithItems): Boolean {
        return oldItem == newItem
    }
}

class CategoryClickListener {
    fun onClick(recyclerHomeItem : RecyclerView) {
        recyclerHomeItem.visibility = if (recyclerHomeItem.isShown) View.GONE else View.VISIBLE
    }
}
