package com.flexath.material3.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person4
import androidx.compose.material.icons.sharp.FaceRetouchingNatural
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexath.material3.ui.theme.MyJetpackComposeAppTheme

class DetailActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
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
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            FilledTextFieldsSection(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            OutlinedTextFieldSection(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun FilledTextFieldsSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var text by remember {
        mutableStateOf("")
    }

    var isError by remember {
        mutableStateOf(false)
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
            isError = text.isEmpty()
        },
        textStyle = TextStyle.Default.copy(
            textAlign = TextAlign.Start
        ),
        modifier = modifier.clip(RoundedCornerShape(10.dp)),
        enabled = true,
        readOnly = false,
        label = {
            Text(text = "Enter your name")
        },
        placeholder = {
            Text(
                text = "Eg - Aung Thiha",
                color = Color.Gray
            )
        },
        leadingIcon = {
            Icon(imageVector = Icons.Sharp.FaceRetouchingNatural, contentDescription = "")
        },
        trailingIcon = {
            Icon(imageVector = Icons.Outlined.Person4, contentDescription = "")
        },
        prefix = {
            Text(
                text = "Mr."
            )
        },
        suffix = {
            Text(
                text = "Cris"
            )
        },
        supportingText = {
            if (isError) {
                Text(
                    text = "Name must be 2 words",
                    color = Color.Red
                )
            } else {
                Text(
                    text = "Name section"
                )
            }
        },
        isError = isError,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                Toast.makeText(context,"Done!",Toast.LENGTH_SHORT).show()
            }),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            focusedContainerColor = Color.Gray,
            unfocusedContainerColor = Color.White,
        )
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.clickable {
            context.startActivity(SelectionComponentActivity.newIntent(context))
        }
    )

    Spacer(modifier = Modifier.height(20.dp))

}

@Composable
fun OutlinedTextFieldSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var text by remember {
        mutableStateOf("")
    }

    var isError by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            isError = text.isEmpty()
        },
        textStyle = TextStyle.Default.copy(
            textAlign = TextAlign.Start
        ),
        modifier = modifier,
        enabled = true,
        readOnly = false,
        label = {
            Text(text = "Enter your name")
        },
        placeholder = {
            Text(
                text = "Eg - Aung Thiha",
                color = Color.Gray
            )
        },
        leadingIcon = {
            Icon(imageVector = Icons.Sharp.FaceRetouchingNatural, contentDescription = "")
        },
        trailingIcon = {
            Icon(imageVector = Icons.Outlined.Person4, contentDescription = "")
        },
        prefix = {
            Text(
                text = "Mr."
            )
        },
        suffix = {
            Text(
                text = "Cris"
            )
        },
        supportingText = {
            if (isError) {
                Text(
                    text = "Name must be 2 words",
                    color = Color.Red
                )
            } else {
                Text(
                    text = "Name section"
                )
            }
        },
        isError = isError,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                Toast.makeText(context,"Done!",Toast.LENGTH_SHORT).show()
            })
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyJetpackComposeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FilledTextFieldsSection()
            OutlinedTextFieldSection()
        }
    }
}