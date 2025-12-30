package com.example.datemanager.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datemanager.viewmodels.ItemEntryViewModel
import com.example.datemanager.viewmodels.ItemNameViewModel

@Composable
fun ProductName(itemEntryViewModel: ItemEntryViewModel, onValueChange: (String) -> Unit, itemNameViewModel: ItemNameViewModel = viewModel() ) {

    val uiState by itemNameViewModel.uiState.collectAsState()

    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                    itemNameViewModel.fetchItemName(itemEntryViewModel.eanInput)
            },
            ) {
            Text("Fetch Name")
        }
        if (uiState.isLoading) {
            Text("Loading...",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            )
        } else if (uiState.errorMessage != null) {
            Text("Error fetching name",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp))
        }

    }

    if (uiState.itemName.product["product_name"] != null) {
        Text("Product Found: ${uiState.itemName.product["product_name"].toString().replace("\"","")}")
        Button(
            onClick = {
                itemEntryViewModel.productName = uiState.itemName.product["product_name"].toString().replace("\"","")
            }
        ) {
            Text("Apply")
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Product Name") },
        value = itemEntryViewModel.productName,
        //value = if (uiState.itemName.product["product_name"] != null) uiState.itemName.product["product_name"].toString() else productName,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

    )
}