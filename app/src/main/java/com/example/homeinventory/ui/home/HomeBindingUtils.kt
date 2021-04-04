package com.example.homeinventory.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeinventory.model.CategoryWithItems
import com.example.homeinventory.model.HomeItem

@BindingAdapter("homeCategoryName")
fun TextView.setCategoryName(homeItem : CategoryWithItems?) {
    homeItem?.let{
        text = it.category.name
    }
}

@BindingAdapter("homeItems")
fun RecyclerView.setHomeItems(homeItem: CategoryWithItems?) {
    if (!homeItem?.homeItems.isNullOrEmpty()) {
        val homeAdapter = HomeRecyclerViewAdapter()
        adapter = homeAdapter
        homeAdapter.submitList(homeItem?.homeItems)
    }
}

@BindingAdapter("itemName")
fun TextView.setHomeItemName(homeItem : HomeItem?) {
    homeItem?.let{
        text = it.itemName
    }
}

@BindingAdapter("itemQty")
fun TextView.setHomeQty(homeItem : HomeItem?) {
    homeItem?.let{
        text = it.itemQuantity.toString()
    }
}