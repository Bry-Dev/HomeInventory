package com.example.homeinventory.model

import androidx.room.*
import java.time.LocalDate
import java.util.*


@Entity(
    tableName = "shopping_items",
    indices = [Index(value = ["home_item_id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = HomeItem::class,
        parentColumns = arrayOf("item_id"),
        childColumns = arrayOf("home_item_id"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class ShoppingItem(
    @ColumnInfo(name = "home_item_id")
    val homeItemsId: Int,
    @ColumnInfo(name = "item_buy_quantity")
    var itemBuyQuantity: Int,
    @ColumnInfo(name = "item_buy_date")
    var itemBuyDate: LocalDate,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shopping_id")
    var itemId: Int = 0
)