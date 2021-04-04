package com.example.homeinventory.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithItems(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "cate_id"
    ) val homeItems: List<HomeItem>
)
