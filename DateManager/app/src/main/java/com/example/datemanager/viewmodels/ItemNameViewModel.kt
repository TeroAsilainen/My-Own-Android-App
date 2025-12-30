package com.example.datemanager.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datemanager.data.ItemNameRepository
import com.example.datemanager.models.ItemName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

data class ItemNameUiState(
    val itemName: ItemName = ItemName(JsonObject(emptyMap())),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ItemNameViewModel: ViewModel() {
    private val repository = ItemNameRepository()
    private val _uiState = MutableStateFlow(ItemNameUiState())

    val uiState: StateFlow<ItemNameUiState> = _uiState.asStateFlow()



    fun fetchItemName(eanCode: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                Log.d("VIEWMODEL", eanCode)
                val itemName = repository.getItemName(eanCode)
                _uiState.value = _uiState.value.copy(itemName = itemName, isLoading = false)
                Log.d("VIEWMODEL", uiState.value.itemName.product["product_name"].toString())
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    itemName = ItemName(JsonObject(emptyMap())),
                    isLoading = false,
                    errorMessage = "Error: ${e.message}"
                )
                e.printStackTrace()
            }
        }
    }
}