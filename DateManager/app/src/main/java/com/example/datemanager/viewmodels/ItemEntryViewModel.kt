package com.example.datemanager.viewmodels

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.datemanager.ui.components.convertMillisToDate

class ItemEntryViewModel: ViewModel() {
    var eanInput by mutableStateOf("")
    var productName by mutableStateOf("")

    var expDateInput by mutableStateOf("")


    //private var _expDate = expDateInput.selectedDateMillis?.let { convertMillisToDate(it) } ?: ""
}