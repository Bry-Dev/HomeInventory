package com.example.homeinventory.repository

import androidx.annotation.WorkerThread
import com.example.homeinventory.dao.HomeItemDao
import com.example.homeinventory.model.CategoryWithItems
import com.example.homeinventory.model.HomeItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HomeRepository
@Inject
constructor(private var homeItemDao: HomeItemDao) {

    val allHomeItem : Flow<List<CategoryWithItems>> = homeItemDao.selectAllHomeItemWithCategory()

    val allHomeItemList : Flow<List<HomeItem>> = homeItemDao.selectAllHomeItems()

    @WorkerThread
    fun hasItem(itemName : String) : Boolean {
        return homeItemDao.hasItem(itemName)
    }

    @WorkerThread
    suspend fun insertHomeItem(homeItem: HomeItem) {
        homeItemDao.insertHomeItem(homeItem)
    }

    @WorkerThread
    suspend fun deleteHomeItem(homeItem: HomeItem) {
        homeItemDao.deleteHomeItem(homeItem)
    }

    @WorkerThread
    suspend fun updateQuantity(itemQty: Int, itemId : Int) {
        homeItemDao.updateQuantity(itemQty, itemId)
    }


    @WorkerThread
    suspend fun updateHomeItem(homeItem: HomeItem) {
        homeItemDao.updateHomeItem(homeItem)
    }

    @WorkerThread
    suspend fun deleteAll() {
        homeItemDao.deleteAll()
    }

}