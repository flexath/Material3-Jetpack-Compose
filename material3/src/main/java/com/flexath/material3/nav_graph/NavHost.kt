package com.flexath.material3.nav_graph

import ChatScreen
import SettingScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavHost(navHostController: NavHostController, values: PaddingValues) {

    androidx.navigation.compose.NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        builder = {
            composable(
                route = Screen.Home.route
            ) {
                HomeScreen(navController = navHostController)
            }

            composable(
                route = Screen.Chat.route
            ) {
                ChatScreen(navController = navHostController)
            }

            composable(
                route = Screen.Setting.route
            ) {
                SettingScreen(navController = navHostController)
            }
        },
        modifier = Modifier.padding(values)
    )
}

@Preview
@Composable
fun ShowNavHostPreview() {
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(rememberNavController(), PaddingValues())
    }
}