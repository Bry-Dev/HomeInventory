package com.example.homeinventory.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.homeinventory.model.Category
import com.example.homeinventory.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject
constructor(private val categoryRepository: CategoryRepository) : ViewModel() {

    val allCategory : LiveData<List<Category>> = categoryRepository.allCategory.asLiveData()

    fun insertCategoryData(category: Category) =
        viewModelScope.launch { categoryRepository.insertCategoryData(category) }

    fun deleteCategory(category: Category) =
        viewModelScope.launch { categoryRepository.deleteCategory(category) }
}