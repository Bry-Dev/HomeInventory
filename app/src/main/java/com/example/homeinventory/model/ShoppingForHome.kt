package com.example.homeinventory.model

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class ShoppingForHome(
    @Embedded val shoppingItem: ShoppingItem ,
    @Relation(
        parentColumn = "home_item_id",
        entityColumn = "item_id"
    ) val homeItem: HomeItem
)
