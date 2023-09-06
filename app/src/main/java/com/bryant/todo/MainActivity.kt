package com.bryant.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bryant.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TodoList()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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

@Composable
fun TodoList() {
    var isEdit by remember { mutableStateOf(true) }

    LazyColumn {
        items(40) {
            index -> Todo(text = "Item $index")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TodoList()
        }
    }
}