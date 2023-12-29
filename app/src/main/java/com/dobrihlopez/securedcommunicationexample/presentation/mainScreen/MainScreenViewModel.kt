package com.dobrihlopez.securedcommunicationexample.presentation.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dobrihlopez.securedcommunicationexample.data.KeysPairManager
import com.dobrihlopez.securedcommunicationexample.data.network.ApiService
import com.dobrihlopez.securedcommunicationexample.domain.KeysBunch
import com.dobrihlopez.securedcommunicationexample.domain.KeysStorage
import com.dobrihlopez.securedcommunicationexample.domain.PrivateKey
import com.dobrihlopez.securedcommunicationexample.domain.PublicKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val keysStorage: KeysStorage,
    private val service: ApiService
) : ViewModel() {
    private val _state = MutableStateFlow<ScreenState>(ScreenState.Idle)
    val state = _state.asStateFlow()

    fun onIntent(intent: ScreenIntent) {
        when (intent) {
            ScreenIntent.FetchData -> fetchData()
        }
    }

    private var keys: KeysBunch? = null

    private fun fetchData() {
        _state.update { ScreenState.Loading }
        viewModelScope.launch {
            delay(1000) // intentional interaction with server overhead
            //test()
            KeysPairManager.generateKeyPair()
            val publicKey = KeysPairManager.getPublicKey()
            val response = service.getData(publicKey)
            Log.d(TAG, "fetchData: $response")
        }
    }

    private fun test() {
        when (Random.nextInt() % 2) {
            0 -> _state.update { ScreenState.Error("Error occurred") }
            1 -> _state.update {
                ScreenState.Success(
                    KeysBunch(
                        PublicKey("public_key"),
                        PrivateKey("private_key")
                    )
                )
            }
        }
    }

    companion object {
        private val TAG = MainScreenViewModel::class.java.simpleName
    }
}