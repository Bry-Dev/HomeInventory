package com.example.homeinventory.ui.shopping

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.homeinventory.R
import com.example.homeinventory.model.ShoppingForHome
import java.time.LocalDate

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
        val shopDate = it.shoppingItem.itemBuyDate.toString().trim()
        text = shopDate
        val toDate = LocalDate.now()
        if (LocalDate.parse(shopDate) < toDate) setTextColor(ContextCompat.getColor(context, R.color.attention))
    }
}