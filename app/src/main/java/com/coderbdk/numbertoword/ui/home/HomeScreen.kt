package com.coderbdk.numbertoword.ui.home

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderbdk.numbertoword.R
import com.coderbdk.numbertoword.ui.theme.NumberToWordTheme
import com.coderbdk.numwordconverter.MaxLimit


@Composable
fun HomeScreen(
    uiState: UiState,
    menuTypes: List<String>,
    showType: (Boolean) -> Unit,
    onTypeSelected: (Int) -> Unit,
    onInputValueChange: (TextFieldValue) -> Unit,
    onValueSwap: () -> Unit,
    convert: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TypeDropdownMenu(
            entries = menuTypes,
            expanded = uiState.expandedType,
            selectedType = uiState.selectedType,
            showType = showType,
            onTypeSelected = onTypeSelected
        )
        Spacer(Modifier.height(16.dp))
        uiState.message?.let {
            Text("${uiState.message}", fontSize = 12.sp)
        }
        HomeScreenContent(
            inputValue = uiState.inputValue,
            outputValue = uiState.outputValue,
            message = uiState.message,
            onValueChange = onInputValueChange,
            onValueSwap = onValueSwap
        )
        Button(
            onClick = convert,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }
    }
}


@Composable
fun TypeDropdownMenu(
    entries: List<String>,
    expanded: Boolean,
    selectedType: Int,
    showType: (Boolean) -> Unit,
    onTypeSelected: (Int) -> Unit
) {

    Box {
        OutlinedTextField(
            value = entries[selectedType],
            onValueChange = {},
            readOnly = true,
            label = {
                Text("Choose Converter Type")
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(selectedType) {
                    awaitEachGesture {
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent =
                            waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            showType(true)
                        }
                    }
                }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                showType(false)
            }
        ) {
            entries.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item)
                    },
                    onClick = {
                        onTypeSelected(index)
                        showType(false)
                    }
                )
            }

        }
    }
}

@Composable
fun HomeScreenContent(
    inputValue: TextFieldValue,
    outputValue: TextFieldValue,
    message: String?,
    onValueChange: (TextFieldValue) -> Unit,
    onValueSwap: () -> Unit,
) {
    Box {
        Column {
            OutlinedTextField(
                value = inputValue,
                onValueChange = onValueChange,
                isError = message != null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                placeholder = {
                    Text("Input")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(painter = painterResource(R.drawable.baseline_content_paste_24), "copy")
                    }
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = outputValue,
                onValueChange = onValueChange,
                readOnly = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                placeholder = {
                    Text("Output")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(painter = painterResource(R.drawable.outline_content_copy_24), "copy")
                    }

                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(vertical = 8.dp)
            )

        }
        FilledIconButton(
            onClick = onValueSwap,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(painter = painterResource(R.drawable.outline_swap_vert_24), "swap")
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    NumberToWordTheme(
        dynamicColor = false
    ) {
        HomeScreen(
            uiState = UiState(
                expandedType = false,
                maxLimit = MaxLimit(8, Int.MAX_VALUE)
            ),
            menuTypes = listOf("Bangla", "English"),
            showType = { },
            onTypeSelected = {},
            onInputValueChange = {},
            onValueSwap = {},
            convert = {}
        )
    }
}