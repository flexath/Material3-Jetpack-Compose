package com.flexath.material3.nav_graph

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flexath.material3.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        var isSheetOpen by rememberSaveable {
            mutableStateOf(false)
        }

        val sheetState = rememberModalBottomSheetState()

        val scaffoldState = rememberBottomSheetScaffoldState()
        val coroutineScope = rememberCoroutineScope()

//        BottomSheetScaffold(
//            scaffoldState = scaffoldState,
//            sheetContent = {
//                Column(
//                    modifier = Modifier.fillMaxSize().background(Color.Yellow)
//                ) {
//                    Text(
//                        text = "Male",
//                        fontSize = 20.sp
//                    )
//
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    Image(
//                        painter = painterResource(id = R.drawable.lab),
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight()
//                    )
//
//                    Spacer(modifier = Modifier.height(20.dp))
//                }
//
//            },
//            sheetPeekHeight = 0.dp,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Box (
//                contentAlignment = Alignment.Center
//            ) {
//                Button(
//                    onClick = {
//                        coroutineScope.launch {
//                            scaffoldState.bottomSheetState.expand()
//                        }
//                    }) {
//                    Text("Open Bottom Sheet 2")
//                }
//            }
//        }

        Column {
            Button(
                onClick = {
                    isSheetOpen = true
                },
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text("Open Bottom Sheet 1")
            }
        }

        if (isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = {
                    isSheetOpen = false
                },
                sheetState = sheetState
            ) {
                Text(
                    text = "Male",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.lab),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview
@Composable
fun ShowHomeTextPreview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreen(rememberNavController())
    }
}