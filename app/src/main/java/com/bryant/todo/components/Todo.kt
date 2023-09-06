package com.bryant.todo.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun Todo(text: String) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text, TextRange(text.length))) }
    var isEdit by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()

    LaunchedEffect(isEdit) {
        if (isEdit) {
            focusRequester.requestFocus()
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isEdit) {
            BasicTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 15.dp)
                    .focusRequester(focusRequester)
            )
        } else {
            Text(
                text = textFieldValue.text,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 15.dp)
            )
        }
        IconButton(onClick = { isEdit = !isEdit }) {
            Icon(
                Icons.Rounded.Edit,
                contentDescription = "Edit To Do"
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Rounded.Delete,
                contentDescription = "Delete To Do"
            )
        }
    }
}