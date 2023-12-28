package com.dobrihlopez.securedcommunicationexample.presentation.mainScreen

import com.dobrihlopez.securedcommunicationexample.R
import com.dobrihlopez.securedcommunicationexample.domain.KeysModel

//sealed interface ScreenState<out V> {
//    class Idle(val errorInfo: String) : ScreenState<Nothing>
//    data object Loading : ScreenState<Nothing>
//    data class Success<V>(val data: V) : ScreenState<V>
//}

sealed interface ScreenState {
    data object Idle : ScreenState
    data object Loading : ScreenState
    data class Success(val data: KeysModel) : ScreenState
    data class Error(val errorInfo: String): ScreenState
}

val buttonFetchDataText = hashMapOf(
    Pair(ScreenState.Idle::class.java, R.string.fetch_data),
    Pair(ScreenState.Loading::class.java, R.string.being_fetched),
    Pair(ScreenState.Success::class.java, R.string.perform_new_fetching),
    Pair(ScreenState.Error::class.java, R.string.try_to_fetch_again),
)

sealed interface ScreenIntent {
    data object FetchData : ScreenIntent
}