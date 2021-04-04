package com.example.homeinventory.dao

import androidx.room.*
import com.example.homeinventory.model.ShoppingForHome
import com.example.homeinventory.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Query("select * from shopping_items")
    fun selectAllShoppingItem(): Flow<List<ShoppingItem>>

    @Transaction
    @Query("select * from shopping_items")
    fun selectAllShoppingForHome() : Flow<List<ShoppingForHome>>

    //@Query("select * from shopping_items where item_name=:name")
    //fun selectShoppingItem(name: String) : ShoppingItem

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("delete from shopping_items")
    suspend fun deleteAll()

    @Update
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem)
}