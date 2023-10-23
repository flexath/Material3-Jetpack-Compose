package com.flexathimyanmar.myjetpackcomposeapp.nav_graph

const val ARG_ITEM_INDEX = "item_index"
const val ARG_ITEM_NAME = "item_name"

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")

    object Task : Screen(route = "task_screen?$ARG_ITEM_INDEX={$ARG_ITEM_INDEX}&$ARG_ITEM_NAME={$ARG_ITEM_NAME}") {
        fun passByIdFromHome(id: Int = 0, name: String): String {
            return "task_screen?$ARG_ITEM_INDEX=$id&$ARG_ITEM_NAME=$name"
        }
    }

    object Detail : Screen(route = "detail_screen")
}
