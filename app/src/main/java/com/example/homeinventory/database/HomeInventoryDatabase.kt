package com.example.homeinventory.database

import androidx.room.*
import com.example.homeinventory.converters.DateConverters
import com.example.homeinventory.dao.CategoryDao
import com.example.homeinventory.dao.HomeItemDao
import com.example.homeinventory.dao.ShoppingItemDao
import com.example.homeinventory.model.Category
import com.example.homeinventory.model.HomeItem
import com.example.homeinventory.model.ShoppingItem


@Database(
    entities = [Category::class, HomeItem::class, ShoppingItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class HomeInventoryDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun homeItemsDao(): HomeItemDao
    abstract fun shoppingItemsDao(): ShoppingItemDao

}