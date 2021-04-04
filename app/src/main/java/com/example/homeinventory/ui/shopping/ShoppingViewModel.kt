package com.example.homeinventory.ui.shopping

import androidx.lifecycle.*
import com.example.homeinventory.model.ShoppingForHome
import com.example.homeinventory.model.ShoppingItem
import com.example.homeinventory.repository.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel
@Inject
constructor(private val shoppingRepository: ShoppingRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is shopping Fragment"
    }

    val allShoppingItem: LiveData<List<ShoppingItem>> = shoppingRepository.allShoppingItem.asLiveData()

    val allShoppingItemForHome : LiveData<List<ShoppingForHome>> = shoppingRepository.allShoppingItemForHome.asLiveData()

    fun insertShoppingItem(shoppingItem: ShoppingItem) =
        viewModelScope.launch { shoppingRepository.insertShoppingItem(shoppingItem) }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) =
        viewModelScope.launch { shoppingRepository.deleteShoppingItem(shoppingItem) }
}