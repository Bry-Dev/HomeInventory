package com.example.homeinventory.repository

import androidx.annotation.WorkerThread
import com.example.homeinventory.dao.ShoppingItemDao
import com.example.homeinventory.model.HomeItem
import com.example.homeinventory.model.ShoppingForHome
import com.example.homeinventory.model.ShoppingItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingRepository
@Inject
constructor(private var shoppingItemDao: ShoppingItemDao) {

    val allShoppingItem : Flow<List<ShoppingItem>> = shoppingItemDao.selectAllShoppingItem()

    val allShoppingItemForHome : Flow<List<ShoppingForHome>> = shoppingItemDao.selectAllShoppingForHome()

    @WorkerThread
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDao.insertShoppingItem(shoppingItem)
    }

    @WorkerThread
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDao.deleteShoppingItem(shoppingItem)
    }

    @WorkerThread
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDao.updateShoppingItem(shoppingItem)
    }

    @WorkerThread
    suspend fun deleteAll() {
        shoppingItemDao.deleteAll()
    }
}