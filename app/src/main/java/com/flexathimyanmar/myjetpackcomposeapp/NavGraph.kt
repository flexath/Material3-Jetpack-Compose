package com.flexathimyanmar.myjetpackcomposeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.flexathimyanmar.myjetpackcomposeapp.nav_graph.ARG_ITEM_INDEX
import com.flexathimyanmar.myjetpackcomposeapp.nav_graph.ARG_ITEM_NAME
import com.flexathimyanmar.myjetpackcomposeapp.nav_graph.Screen

@Composable
fun NavHost(navController: NavHostController) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        androidx.navigation.compose.NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            builder = {
                composable(route = Screen.Home.route) {
                    Home(navController)
                }

                composable(
                    route = Screen.Task.route,
                    arguments = listOf(
                        navArgument(ARG_ITEM_INDEX) {
                            type = NavType.IntType
                            defaultValue = 0
                        },
                        navArgument(ARG_ITEM_NAME) {
                            type = NavType.StringType
                            defaultValue = "Hnin Nu"
                        }
                    )
                ) {
                    val itemIndex = it.arguments?.getInt(ARG_ITEM_INDEX) ?: 0
                    val itemName = it.arguments?.getString(ARG_ITEM_NAME) ?: ""
                    Task(itemIndex,itemName,navController)
                }

                composable(
                    route = Screen.Detail.route
                ) {
                    Detail(navController = navController)
                }
            }
        )
    }
}