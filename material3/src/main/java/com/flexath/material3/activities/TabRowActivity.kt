package com.flexath.material3.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.material3.ui.theme.MyJetpackComposeAppTheme

class TabRowActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TabRowActivity::class.java)
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
                    TabRowSetup()
                }
            }
        }
    }
}

data class TabItem(
    var title: String,
    var selectedIcon: ImageVector,
    var unSelectedIcon: ImageVector
)

val items = listOf(
    TabItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),
    TabItem(
        title = "Shop",
        selectedIcon = Icons.Filled.ShoppingCart,
        unSelectedIcon = Icons.Outlined.ShoppingCart
    ),
    TabItem(
        title = "Internet",
        selectedIcon = Icons.Filled.LibraryMusic,
        unSelectedIcon = Icons.Outlined.LibraryMusic
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowSetup() {

    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        items.size
    }

    LaunchedEffect(selectedItemIndex) {
        pagerState.animateScrollToPage(selectedItemIndex)
    }

    LaunchedEffect(pagerState.currentPage,pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) {
            selectedItemIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = selectedItemIndex
        ) {
            items.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == selectedItemIndex,
                    onClick = {
                        selectedItemIndex = index
                    },
                    text = {
                        Text(text = tabItem.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                tabItem.selectedIcon
                            } else {
                                tabItem.unSelectedIcon
                            },
                            contentDescription = null
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState
        ) {index ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(
                    text = items[index].title
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    MyJetpackComposeAppTheme {

    }
}