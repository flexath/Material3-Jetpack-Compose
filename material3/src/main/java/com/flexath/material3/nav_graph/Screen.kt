package com.flexath.material3.nav_graph

const val HOME_SCREEN = "home_screen"
const val CHAT_SCREEN = "chat_screen"
const val SETTING_SCREEN = "setting_screen"

sealed class Screen(val route:String) {
    object Home : Screen(HOME_SCREEN)
    object Chat : Screen(CHAT_SCREEN)
    object Setting : Screen(SETTING_SCREEN)
}
