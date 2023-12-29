package com.dobrihlopez.securedcommunicationexample.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dobrihlopez.securedcommunicationexample.R
import com.dobrihlopez.securedcommunicationexample.domain.KeysBunch
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
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is ScreenState.Idle -> {
                Text(
                    text = stringResource(R.string.welcome_message),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            ScreenState.Loading -> {
                CircularProgressIndicator()
            }

            is ScreenState.Success -> {
                Text(
                    text = stringResource(R.string.data_has_been_successfully_fetched),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(
                        R.string.public_key_private_key,
                        state.data.first.key,
                        state.data.second.key
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            is ScreenState.Error -> {
                Icon(
                    modifier = Modifier
                        .size(100.dp)
                        .alpha(0.15f),
                    painter = painterResource(id = R.drawable.round_warning_amber_24),
                    contentDescription = stringResource(R.string.error_image),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))

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
            KeysBunch(
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
            KeysBunch(
                first = PublicKey(key = "ornare"),
                second = PrivateKey(key = "minim")
            )
        ), onFetchData = {})
    }
}