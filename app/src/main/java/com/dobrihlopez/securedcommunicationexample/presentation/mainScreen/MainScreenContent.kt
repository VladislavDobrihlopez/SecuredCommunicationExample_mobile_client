package com.dobrihlopez.securedcommunicationexample.presentation.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dobrihlopez.securedcommunicationexample.domain.KeysModel
import com.dobrihlopez.securedcommunicationexample.domain.PrivateKey
import com.dobrihlopez.securedcommunicationexample.domain.PublicKey
import com.dobrihlopez.securedcommunicationexample.presentation.mainScreen.composable.FetchButton
import com.dobrihlopez.securedcommunicationexample.ui.theme.SecuredCommunicationExampleTheme

@Composable
fun MainScreenContent(
    state: ScreenState,
    onFetchData: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is ScreenState.Idle -> {
                Text(text = "Hello. This is an example of interacting with the Ktor server easy and in secure manner")
            }

            ScreenState.Loading -> {
                CircularProgressIndicator()
            }

            is ScreenState.Success -> {
                Text(text = "Data has been successfully fetched")
                Text(text = "Public key: ${state.data.first.key}. Private key: ${state.data.second.key}")
            }

            is ScreenState.Error -> {

            }
        }

        FetchButton(
            text = stringResource(id = requireNotNull(buttonFetchDataText[state::class.java])),
            onTouch = onFetchData
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMainScreenContent_darkTheme() {
    SecuredCommunicationExampleTheme(darkTheme = true) {
        MainScreenContent(state = ScreenState.Success(
            KeysModel(
                first = PublicKey(key = "ornare"),
                second = PrivateKey(key = "minim")
            )
        ), onFetchData = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMainScreenContent_lightTheme() {
    SecuredCommunicationExampleTheme(darkTheme = false) {
        MainScreenContent(state = ScreenState.Success(
            KeysModel(
                first = PublicKey(key = "ornare"),
                second = PrivateKey(key = "minim")
            )
        ), onFetchData = {})
    }
}