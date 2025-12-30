package com.example.datemanager.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datemanager.ui.components.EANField
import com.example.datemanager.ui.components.ExpDate
import com.example.datemanager.ui.components.ProductName
import com.example.datemanager.viewmodels.ItemEntryViewModel


@Composable
fun AddNewScreen(context: Context, modifier: Modifier, itemEntryViewModel: ItemEntryViewModel = viewModel() ) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            EANField(context ,itemEntryViewModel.eanInput, { itemEntryViewModel.eanInput = it })
        }

        ProductName(
            itemEntryViewModel = itemEntryViewModel,
            onValueChange = {itemEntryViewModel.productName = it }
        )

        ExpDate(itemEntryViewModel =  itemEntryViewModel, onValueChange =  { itemEntryViewModel.expDateInput = it })

        if (itemEntryViewModel.eanInput.isNotBlank() && itemEntryViewModel.productName.isNotBlank() && itemEntryViewModel.expDateInput.isNotBlank()) {
            Button(
                onClick = {
                    Log.d("ADD ITEM", itemEntryViewModel.expDateInput)
                }
            ) {
                Text("Add Item")
            }
        }



    }


}





