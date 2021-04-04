package com.example.homeinventory.repository

import androidx.annotation.WorkerThread
import com.example.homeinventory.dao.CategoryDao
import com.example.homeinventory.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository
@Inject
constructor(private var categoryDao: CategoryDao) {

    val allCategory : Flow<List<Category>> = categoryDao.selectAllCategory()

    @WorkerThread
    suspend fun insertCategoryData(category: Category) {
        categoryDao.insertCategoryData(category)
    }

    @WorkerThread
    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    @WorkerThread
    suspend fun deleteAll() {
        categoryDao.deleteAll()
    }
}