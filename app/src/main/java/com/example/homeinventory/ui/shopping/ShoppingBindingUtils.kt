package com.example.homeinventory.ui.shopping

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.homeinventory.model.ShoppingForHome

@BindingAdapter("shoppingItemName")
fun TextView.setShoppingName(shoppingForHome : ShoppingForHome) {
    shoppingForHome.let{
        text = it.homeItem.itemName
    }
}

@BindingAdapter("shoppingItemQty")
fun TextView.setShoppingQty(shoppingForHome : ShoppingForHome) {
    shoppingForHome.let{
        text = it.shoppingItem.itemBuyQuantity.toString()
    }
}

@BindingAdapter("shoppingItemDate")
fun TextView.setShoppingDate(shoppingForHome : ShoppingForHome) {
    shoppingForHome.let{
        text = it.shoppingItem.itemBuyDate.toString()
    }
}