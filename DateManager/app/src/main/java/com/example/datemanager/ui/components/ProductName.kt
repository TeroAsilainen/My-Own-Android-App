package com.example.datemanager.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datemanager.R
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
                    itemNameViewModel.fetchItemName(itemEntryViewModel.itemUiState.itemDetails.ean)
            },
            ) {
            Text(stringResource(R.string.hae_tuote))
        }
        if (uiState.isLoading) {
            Text(
                stringResource(R.string.lataa),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        } else if (uiState.errorMessage != null) {
            Text(
                stringResource(R.string.virhe),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.primary)
        }

    }

    if (uiState.itemName.product["product_name"] != null) {
        val newProductName = uiState.itemName.product["brands"].toString().replace("\"","") + " " + uiState.itemName.product["product_name"].toString().replace("\"","")
        Text(stringResource(R.string.l_ytyi_tuote, newProductName),
            color = MaterialTheme.colorScheme.primary)
        Button(
            onClick = {
                itemEntryViewModel.updateUiState(itemEntryViewModel.itemUiState.itemDetails.copy(itemName = newProductName))
            }
        ) {
            Text(stringResource(R.string.k_yt))
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.tuotteen_nimi)) },
        value = itemEntryViewModel.itemUiState.itemDetails.itemName,
        //value = if (uiState.itemName.product["product_name"] != null) uiState.itemName.product["product_name"].toString() else productName,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary),
    )
}