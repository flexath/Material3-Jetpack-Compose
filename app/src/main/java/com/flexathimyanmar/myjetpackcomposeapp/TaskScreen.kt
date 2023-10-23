package com.flexathimyanmar.myjetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flexathimyanmar.myjetpackcomposeapp.nav_graph.Screen

class TaskScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun Task(item: Int, name: String, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "$item\n$name",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentWidth().clickable {
                navController.navigate(route = Screen.Detail.route)
            }
        )
    }
}

@Preview
@Composable
fun ShowTextPreview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Task(0, "", rememberNavController())
    }
}