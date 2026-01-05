package com.example.datemanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.datemanager.R
import com.example.datemanager.data.DbItem
import com.example.datemanager.viewmodels.ItemEntryViewModel

@Composable
fun HomeRow(item: DbItem, viewModel: ItemEntryViewModel) {
    var isExpanded by remember { mutableStateOf(false) }

    Surface(
        Modifier
            .clickable(
                onClick = { isExpanded = !isExpanded }
            )
            .fillMaxWidth()
            .padding(8.dp)
            ,
        color = MaterialTheme.colorScheme.secondary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        item.expDate,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        item.itemName,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

                if (isExpanded) {
                    Row(
                        Modifier.fillMaxWidth()
                    ) {
                        Text(item.ean,
                            color = MaterialTheme.colorScheme.onSecondary)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                viewModel.deleteItem(item)
                            },

                        ) { Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_item),

                        )  }
                    }
                }
            }
        }
    }
}