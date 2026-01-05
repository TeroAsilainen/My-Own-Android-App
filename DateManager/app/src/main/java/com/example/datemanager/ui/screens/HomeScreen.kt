package com.example.datemanager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.datemanager.ui.components.HomeRow
import com.example.datemanager.viewmodels.ItemEntryViewModel

@Composable
fun HomeScreen(modifier: Modifier, viewModel: ItemEntryViewModel) {
        val list = viewModel.itemsList.collectAsState(initial = emptyList())

        LazyColumn(
            modifier = modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(list.value) { idx, item ->
                HomeRow(item, viewModel)
            }
        }

}