package com.example.datemanager.viewmodels

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datemanager.data.DbItem
import com.example.datemanager.data.DbItemsRepository
import com.example.datemanager.data.InventoryDatabase
import com.example.datemanager.data.ItemDao
import com.example.datemanager.ui.components.convertMillisToDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class ItemEntryViewModel(val dao: ItemDao): ViewModel() {
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    var itemsList = dao.getAllItems()


    fun resetUiState() {
        updateUiState(
            itemDetails = ItemDetails(
                itemName = "",
                ean = "",
                expDate = "")
        )
    }


    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            ean.isNotBlank() && itemName.isNotBlank() && expDate.isNotBlank()
        }
    }

    suspend fun saveItem() {
        if (validateInput()) {
            dao.insert(itemUiState.itemDetails.toDbItem())
        }
    }

    fun deleteItem(dbItem: DbItem) {
        viewModelScope.launch {
            dao.delete(dbItem)
        }
    }


}


data class ItemUiState(
    //val itemsList: List<DbItem> = emptyList(),
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val ean: String = "",
    val itemName: String = "",
    val expDate: String = ""
)

fun ItemDetails.toDbItem(): DbItem = DbItem(
    id = id,
    ean = ean,
    itemName = itemName,
    expDate = expDate
)

fun DbItem.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun DbItem.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    ean = ean,
    itemName = itemName,
    expDate = expDate
)