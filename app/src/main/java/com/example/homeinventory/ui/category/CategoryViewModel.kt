package com.example.homeinventory.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.homeinventory.model.Category
import com.example.homeinventory.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
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

    fun selectCategory(id : Int) : Single<String> {
        return categoryRepository.selectCategory(id)
    }

    fun deleteCategory(category: Category) =
        viewModelScope.launch { categoryRepository.deleteCategory(category) }
}