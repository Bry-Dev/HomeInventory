package com.example.homeinventory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity (tableName = "category_list", indices = [Index(value = ["name"], unique = true)])
data class Category(
    @ColumnInfo(name = "name")
    var name:String ,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val categoryId:Int = 0) {
}