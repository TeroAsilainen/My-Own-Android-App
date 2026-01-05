package com.example.datemanager.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.datemanager.R
import com.example.datemanager.ui.components.EANField
import com.example.datemanager.ui.components.ExpDate
import com.example.datemanager.ui.components.ProductName
import com.example.datemanager.viewmodels.ItemEntryViewModel
import kotlinx.coroutines.launch


@Composable
fun AddNewScreen(
    context: Context,
    modifier: Modifier,
    itemEntryViewModel: ItemEntryViewModel = viewModel(),
    navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            EANField(context ,itemEntryViewModel.itemUiState.itemDetails.ean, { itemEntryViewModel.updateUiState( itemEntryViewModel.itemUiState.itemDetails.copy(ean = it)) })
        }

        ProductName(
            itemEntryViewModel = itemEntryViewModel,
            onValueChange = {itemEntryViewModel.updateUiState(itemEntryViewModel.itemUiState.itemDetails.copy(itemName = it)) }
        )

        ExpDate(
            itemEntryViewModel =  itemEntryViewModel,
            onValueChange =  { itemEntryViewModel.updateUiState(itemEntryViewModel.itemUiState.itemDetails.copy(expDate = it)) })

        if (itemEntryViewModel.itemUiState.isEntryValid) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        itemEntryViewModel.saveItem()
                        navController.navigate("home")
                        itemEntryViewModel.resetUiState()
                    }
                    Log.d("ADD ITEM", itemEntryViewModel.itemUiState.itemDetails.ean)
                    Log.d("ADD ITEM", itemEntryViewModel.itemUiState.itemDetails.itemName)
                    Log.d("ADD ITEM", itemEntryViewModel.itemUiState.itemDetails.expDate)

                }
            ) {
                Text(stringResource(R.string.tallenna))
            }
        }



    }


}





