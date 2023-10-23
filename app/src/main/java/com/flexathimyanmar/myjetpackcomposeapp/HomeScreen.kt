package com.flexathimyanmar.myjetpackcomposeapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.flexathimyanmar.myjetpackcomposeapp.nav_graph.ARG_ITEM_INDEX
import com.flexathimyanmar.myjetpackcomposeapp.nav_graph.Screen
import com.flexathimyanmar.myjetpackcomposeapp.ui.theme.MyJetpackComposeAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class HomeScreen() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChangeStatusBarColor()
            Home()
        }
    }
}

@Composable
fun ChangeStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }
}

data class User(
    val id: Int = 0,
    val name: String
)

val users = listOf(
    User(0 , "Aung Thiha"),
    User(1 , "Zin Bo Khine"),
    User(2 , "Win Lwin"),
    User(3 , "Kyi Khant"),
    User(4 , "Thet Paing Soe"),
    User(5 , "Thar Htet")
)

@Composable
fun Home(navController: NavHostController? = null) {
    val userList = remember {
        mutableStateListOf(User(100,"Hnin Nu"))
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (navController != null) {
            UserCardList(userList = userList, navController)
        }
        Button(
            onClick = {
                userList.add(User(1,"Aung Thiha"))
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            )
        ) {
            Text("Add User")
        }
    }
}

// CardView List RecyclerView
@Composable
fun UserCardList(userList: List<User>, navController: NavHostController) {
    LazyColumn {
        itemsIndexed(userList) { index,user ->
            UserCard(user,index,navController)
        }
    }
}

// For Ui CardView
@Composable
fun UserCard(user: User, index: Int, navController: NavHostController) {
    val context = LocalContext.current

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier.padding(20.dp).clickable {
            val num = 100
            navController.navigate(route = Screen.Task.passByIdFromHome(num,"Cristiano Ronaldo"))
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.lab),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "$user",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
                Button(
                    onClick = {
                        Toast.makeText(context, "Profile opened", Toast.LENGTH_SHORT).show()
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text(
                        text = "View Your Profile",
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

// Use XML in composable
@Composable
fun ComposeWithXML() {
    AndroidView(
        factory = {
            View.inflate(it,R.layout.activity_main,null)
        },
        modifier = Modifier.fillMaxSize(),
        update = {
            val text = it.findViewById<TextView>(R.id.tvClick)
            text.setOnClickListener {
                text.text = "Updated Text"
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyJetpackComposeAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ChangeStatusBarColor()
            Home()
            ComposeWithXML()
        }
    }
}