package com.example.homeinventory.model

import androidx.room.*


@Entity(
    tableName = "home_items", indices = [Index(value = ["cate_id", "item_name"], unique = true)], foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("category_id"),
        childColumns = arrayOf("cate_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class HomeItem(
    @ColumnInfo(name = "cate_id")
    var categoryId: Int,
    @ColumnInfo(name = "item_name")
    var itemName: String,
    @ColumnInfo(name = "item_quantity")
    var itemQuantity: Int ,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    var itemId: Int = 0
) {

}