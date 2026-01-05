package com.example.datemanager.ui.appbars

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import com.example.datemanager.R

@Composable
fun BottomBar(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    val tabs = listOf(
        TabItem(stringResource(R.string.koti), Icons.Filled.Home, route = "home"),
        TabItem(stringResource(R.string.lis_uusi), Icons.Filled.Add, route = "addNew"),
        TabItem(stringResource(R.string.info), Icons.Filled.Info, route = "info"),
    )

    NavigationBar(
       containerColor = MaterialTheme.colorScheme.primary,
    ) {
        tabs.forEach { tab ->
            val selected = tab.route === backStackEntry.value?.destination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { navController.navigate(tab.route)},
                    label = { Text(tab.label) },
                    icon = { Icon(
                        imageVector = tab.icon,
                        contentDescription = null
                    ) },
                    colors = NavigationBarItemColors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        selectedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                        unselectedTextColor = MaterialTheme.colorScheme.tertiary,
                        disabledIconColor = MaterialTheme.colorScheme.tertiary,
                        disabledTextColor = MaterialTheme.colorScheme.tertiary,
                    )
                )
        }
    }
}