package com.example.homeinventory.dao

import androidx.room.*
import com.example.homeinventory.model.Category
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryData(category: Category)

    @Query("select * from category_list")
    fun selectAllCategory(): Flow<List<Category>>

    @Query("select name from category_list where category_id=:id")
    fun selectCategory(id: Int): Single<String>

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("delete from category_list")
    suspend fun deleteAll()

    @Update
    suspend fun updateCategory(category: Category)



}