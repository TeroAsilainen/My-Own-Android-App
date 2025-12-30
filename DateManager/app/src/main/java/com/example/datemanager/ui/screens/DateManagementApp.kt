package com.example.datemanager.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datemanager.models.ItemName
import com.example.datemanager.ui.appbars.BottomBar
import com.example.datemanager.ui.appbars.TopBar


@Composable
fun DateManagementApp(context: Context) {
    val navController = rememberNavController()
    var topLabel by remember { mutableStateOf("Home") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(topLabel)
        },
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        NavHost (
            navController = navController,
            startDestination = "home"
        ) {
            composable(route = "home" ) {
                topLabel = "Home"
                HomeScreen(modifier)
            }
            composable(route = "addNew") {
                topLabel = "Add New Item"
                AddNewScreen(context, modifier)
            }
            composable(route = "info") {
                topLabel = "Info"
                InfoScreen(modifier)
            }
        }
    }
}