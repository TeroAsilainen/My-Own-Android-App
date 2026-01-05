package com.example.datemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.datemanager.data.InventoryDatabase
import com.example.datemanager.ui.screens.DateManagementApp
import com.example.datemanager.ui.theme.DateManagerTheme
import com.example.datemanager.viewmodels.ItemEntryViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            InventoryDatabase::class.java,
            "items.db"
            ).build()
    }

    private val viewModel by viewModels<ItemEntryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ItemEntryViewModel(db.itemDao()) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DateManagerTheme {
                val viewModel = viewModel
                DateManagementApp(this, viewModel)
            }
        }
    }
}

