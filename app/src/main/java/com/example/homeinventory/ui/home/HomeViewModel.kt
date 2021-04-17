package com.example.homeinventory.ui.home

import androidx.lifecycle.*
import com.example.homeinventory.model.CategoryWithItems
import com.example.homeinventory.model.HomeItem
import com.example.homeinventory.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val allHomeItem: LiveData<List<CategoryWithItems>> = homeRepository.allHomeItem.asLiveData()

    private val allHomeItemList : LiveData<List<HomeItem>> = homeRepository.allHomeItemList.asLiveData()

    val allHomeItemName : LiveData<List<String>> = allHomeItemList.map { it.map{ homeItem -> homeItem.itemName } }

    var allHomeItemId : LiveData<Map<Int, String>> = allHomeItemList.map{ it -> it.associate { Pair(it.itemId, it.itemName) }}

    fun hasItem(itemName : String) : Boolean {
        return homeRepository.hasItem(itemName)
    }

    fun updateQuantity(itemQty: Int, itemId : Int) = viewModelScope.launch { homeRepository.updateQuantity(itemQty, itemId) }

    fun insertHomeItem(homeItem: HomeItem) =
        viewModelScope.launch { homeRepository.insertHomeItem(homeItem) }

    fun updateHomeItem(homeItem: HomeItem) = viewModelScope.launch { homeRepository.updateHomeItem(homeItem) }

    fun deleteHomeItem(homeItem: HomeItem) =
        viewModelScope.launch { homeRepository.deleteHomeItem(homeItem) }
}