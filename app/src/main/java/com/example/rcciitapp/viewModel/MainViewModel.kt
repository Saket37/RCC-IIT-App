package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val isConnectivityAvailable: Boolean = false
)

@HiltViewModel
class MainViewModel @Inject constructor() :
    ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {
            //delay(3000)
            _homeUiState.value = _homeUiState.value.copy(isLoading = false)
        }
        //observeConnectivity()
    }


   /* private fun observeConnectivity() {
        connectivityObserver.connectionState
            .distinctUntilChanged()
            .map { it === ConnectionState.Available }
            .onEach { _homeUiState.value = _homeUiState.value.copy(isConnectivityAvailable = it) }
            .launchIn(viewModelScope)
    }*/
}