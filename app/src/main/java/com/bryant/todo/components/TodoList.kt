package com.bryant.todo.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun TodoList() {
    LazyColumn {
        items(40) {
                index -> Todo(text = "Item $index")
        }
    }
}