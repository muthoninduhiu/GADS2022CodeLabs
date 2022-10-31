package com.example.inventory.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao): ViewModel() {
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    /*Populate the InventoryViewModel class to add inventory data to the database.
    Observe the Item entity and Add Item screen in the Inventory app.*/
    private fun insertItem(item: Item) {
        /*To interact with the database off the main thread, start a coroutine
        and call the DAO method within it*/
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewItemEntry(itemName: String, itemPrice: String, itemCount: String): Item {
        return Item(
            itemName = itemName,
            itemPrice =  itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    fun addNewItem(itemName: String,itemPrice: String, itemCount: String) {
       val newItem = getNewItemEntry(itemName, itemPrice,itemCount)
        insertItem(newItem)
    }

    fun isEntryValid(itemName:String, itemPrice: String,itemCount: String): Boolean{
        if (itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()) {
            return false
        }
        return true
    }
}
/*view model instantiation*/
class InventoryViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
