package com.flexath.material3.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Girl
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Girl
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.flexath.material3.nav_graph.NavHost
import com.flexath.material3.nav_graph.Screen
import com.flexath.material3.ui.theme.MyJetpackComposeAppTheme
import kotlinx.coroutines.launch

class BottomNavigationBarActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BottomNavigationBarActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetpackComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navHostController = rememberNavController()
                    BottomNavigationBar(navHostController)
                }
            }
        }
    }
}

data class BottomNavigationItem(
    var title: String,
    var selectedIcon: ImageVector,
    var unselectedIcon: ImageVector,
    var hasNew: Boolean,
    var badgeCount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    val context = LocalContext.current
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNew = false
        ),
        BottomNavigationItem(
            title = "Chat",
            selectedIcon = Icons.Filled.Chat,
            unselectedIcon = Icons.Outlined.Chat,
            hasNew = false,
            badgeCount = 10
        ),
        BottomNavigationItem(
            title = "Setting",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNew = true
        )
    )

    val drawerItems = listOf(
        BottomNavigationItem(
            title = "Aung Thiha",
            selectedIcon = Icons.Filled.Man,
            unselectedIcon = Icons.Outlined.Man,
            hasNew = false
        ),
        BottomNavigationItem(
            title = "Hnin Nu",
            selectedIcon = Icons.Filled.Girl,
            unselectedIcon = Icons.Outlined.Girl,
            hasNew = false,
            badgeCount = 35
        ),
        BottomNavigationItem(
            title = "Lover",
            selectedIcon = Icons.Filled.MonitorHeart,
            unselectedIcon = Icons.Outlined.MonitorHeart,
            hasNew = true
        )
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var selectedBottomItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    var selectedDrawerItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(300.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Profile",
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                drawerItems.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title)
                        },
                        selected = index == selectedDrawerItemIndex,
                        onClick = {
                            selectedDrawerItemIndex = index
                            coroutineScope.launch {
                                drawerState.close()
                            }

                            if (selectedDrawerItemIndex == 1) {
                                context.startActivity(TabRowActivity.newIntent(context))
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedDrawerItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = "Drawer Icons"
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }

                            if(item.hasNew) {
                                Badge()
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Testing App Bar")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Drawer Opened"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    bottomNavigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedBottomItemIndex == index,
                            onClick = {
                                selectedBottomItemIndex = index

                                when (selectedBottomItemIndex) {
                                    0 -> navHostController.navigate(Screen.Home.route)
                                    1 -> navHostController.navigate(Screen.Chat.route)
                                    else -> navHostController.navigate(Screen.Setting.route)
                                }
                            },
                            label = {
                                Text(text = item.title)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNew) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedBottomItemIndex) {
                                            item.selectedIcon
                                        } else {
                                            item.unselectedIcon
                                        },
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ) { values ->
            Log.i("Values",values.toString())
            NavHost(navHostController = navHostController, values = values)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    MyJetpackComposeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BottomNavigationBar(rememberNavController())
        }
    }
}