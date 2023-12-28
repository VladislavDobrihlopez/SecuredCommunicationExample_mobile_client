package com.dobrihlopez.securedcommunicationexample.presentation.mainScreen.composable

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FetchButton(text: String, onTouch: () -> Unit) {
    Button(onClick = onTouch) {
        Text(text = text)
    }
}