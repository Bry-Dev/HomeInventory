package com.example.homeinventory.di

import android.content.Context
import androidx.room.Room
import com.example.homeinventory.BaseApplication
import com.example.homeinventory.dao.CategoryDao
import com.example.homeinventory.dao.HomeItemDao
import com.example.homeinventory.dao.ShoppingItemDao
import com.example.homeinventory.database.HomeInventoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext appContext: Context) : BaseApplication {
        return appContext as BaseApplication
    }

    @Singleton
    @Provides
    fun provideTestString() : String {
        return "Test String"
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) : HomeInventoryDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            HomeInventoryDatabase::class.java,
            "home_inventory_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeItemsDao(database: HomeInventoryDatabase) : HomeItemDao {
        return database.homeItemsDao()
    }

    @Singleton
    @Provides
    fun provideShoppingItemDao(database: HomeInventoryDatabase) : ShoppingItemDao {
        return database.shoppingItemsDao()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(database: HomeInventoryDatabase) : CategoryDao {
        return database.categoryDao()
    }

}