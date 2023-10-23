package com.flexath.material3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexath.material3.ui.theme.MyJetpackComposeAppTheme

class SelectionComponentActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SelectionComponentActivity::class.java)
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        CheckBoxes()

                        Spacer(modifier = Modifier.height(30.dp))

                        Switcher()

                        Spacer(modifier = Modifier.height(30.dp))

                        RadioButtons()
                    }
                }
            }
        }
    }
}

data class ToggleableInfo(
    var isChecked: Boolean,
    var label: String
)

@Composable
fun CheckBoxes() {
    val checkboxes = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = false,
                label = "Photos"
            ),
            ToggleableInfo(
                isChecked = false,
                label = "Videos"
            ),
            ToggleableInfo(
                isChecked = false,
                label = "Audio"
            )
        )
    }

    var triState by remember {
        mutableStateOf(
            ToggleableState.Indeterminate
        )
    }

    val toggleTriState = {
        triState = when (triState) {
            ToggleableState.Indeterminate -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }

        checkboxes.indices.forEach { index ->
            checkboxes[index] = checkboxes[index].copy(
                isChecked = triState == ToggleableState.On
            )
        }
    }

    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .triStateToggleable(
                state = triState,
                onClick = toggleTriState
            )
    ) {
        TriStateCheckbox(
            state = triState,
            onClick = toggleTriState
        )
        Text(
            text = "Media Types"
        )
    }

    checkboxes.forEachIndexed { index, togglableInfo ->
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .padding(start = 30.dp)
                .fillMaxWidth()
                .toggleable(
                    value = togglableInfo.isChecked,
                    onValueChange = {
                        checkboxes[index] = togglableInfo.copy(
                            isChecked = it
                        )
                    }
                )
        ) {
            Checkbox(
                checked = togglableInfo.isChecked,
                onCheckedChange = {
                    checkboxes[index] = togglableInfo.copy(
                        isChecked = it
                    )
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Green,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.Black,
                )
            )

            Text(
                text = togglableInfo.label,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun Switcher() {
    var switch by remember {
        mutableStateOf(
            ToggleableInfo(
                isChecked = false,
                label = "Dark mode"
            )
        )
    }

    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(text = switch.label)
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switch.isChecked,
            onCheckedChange = {
                switch = if (!it) {
                    switch.copy(
                        isChecked = it,
                        label = "Dark Mode"
                    )
                } else {
                    switch.copy(
                        isChecked = it,
                        label = "Light Mode"
                    )
                }

            },
            thumbContent = {
                Icon(
                    imageVector = if (switch.isChecked) {
                        Icons.Default.Check
                    } else {
                        Icons.Default.Close
                    },
                    contentDescription = null
                )
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color.Transparent,
                checkedTrackColor = Color.Black
            )
        )
    }

}

@Composable
fun RadioButtons() {
    val context =  LocalContext.current
    val radioButtons = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = true,
                label = "Photos"
            ),
            ToggleableInfo(
                isChecked = false,
                label = "Videos"
            ),
            ToggleableInfo(
                isChecked = false,
                label = "Audio"
            )
        )
    }

    radioButtons.forEachIndexed { index, togglableInfo ->
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.label == togglableInfo.label
                        )
                    }
                }
        ) {
            RadioButton(
                selected = togglableInfo.isChecked,
                onClick = {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.label == togglableInfo.label
                        )
                    }
                }
            )

            Text(
                text = togglableInfo.label,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    if(radioButtons[2].isChecked) {
        context.startActivity(AppBarsActivity.newIntent(context))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    MyJetpackComposeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CheckBoxes()

                Spacer(modifier = Modifier.height(30.dp))

                Switcher()

                Spacer(modifier = Modifier.height(30.dp))

                RadioButtons()
            }
        }
    }
}