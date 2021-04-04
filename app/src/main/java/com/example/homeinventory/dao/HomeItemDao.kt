package com.example.homeinventory.dao

import androidx.room.*
import com.example.homeinventory.model.CategoryWithItems
import com.example.homeinventory.model.HomeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomeItem(homeItem: HomeItem)

    @Query("select * from home_items")
    fun selectAllHomeItems() : Flow<List<HomeItem>>

    @Query("select item_name from home_items")
    fun selectAllHomeItemName() : Flow<List<String>>

    @Query("select exists(select item_name from home_items where item_name = :itemName)")
    fun hasItem(itemName : String) : Boolean

    @Delete
    suspend fun deleteHomeItem(homeItem: HomeItem)

    @Query("delete from home_items")
    suspend fun deleteAll()

    @Transaction
    @Query("select * from category_list")
    fun selectAllHomeItemWithCategory() : Flow<List<CategoryWithItems>>

    @Query("update home_items set item_quantity = :itemQty where item_id = :itemId")
    suspend fun updateQuantity(itemQty: Int, itemId : Int)

    @Update
    suspend fun updateHomeItem(homeItem: HomeItem)

}