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
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datemanager.R
import com.example.datemanager.ui.appbars.BottomBar
import com.example.datemanager.ui.appbars.TopBar
import com.example.datemanager.viewmodels.ItemEntryViewModel


@Composable
fun DateManagementApp(context: Context, viewModel: ItemEntryViewModel) {
    val navController = rememberNavController()
    var topLabel by remember { mutableStateOf( "Koti") }

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
                topLabel = stringResource(R.string.koti)
                HomeScreen(modifier, viewModel)
            }
            composable(route = "addNew") {
                topLabel = stringResource(R.string.lis_uusi)
                AddNewScreen(context = context, modifier =  modifier, navController = navController, itemEntryViewModel = viewModel)
            }
            composable(route = "info") {
                topLabel = stringResource(R.string.info)
                InfoScreen(modifier)
            }
        }
    }
}