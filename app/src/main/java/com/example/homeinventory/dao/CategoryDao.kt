package com.example.homeinventory.dao

import androidx.room.*
import com.example.homeinventory.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryData(category: Category)

    @Query("select * from category_list")
    fun selectAllCategory(): Flow<List<Category>>

    @Query("select * from category_list where name=:name")
    suspend fun selectCategory(name: String): Category

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("delete from category_list")
    suspend fun deleteAll()

    @Update
    suspend fun updateCategory(category: Category)



}