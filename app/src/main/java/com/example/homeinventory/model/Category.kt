package com.example.homeinventory.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity (tableName = "category_list", indices = [Index(value = ["name"], unique = true)])
@Parcelize
data class Category(
    @ColumnInfo(name = "name")
    var name:String ,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val categoryId:Int = 0) : Parcelable {
}