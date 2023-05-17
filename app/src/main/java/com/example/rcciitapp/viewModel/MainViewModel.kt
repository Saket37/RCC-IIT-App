package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.observeconnectivity.ConnectionState
import com.example.rcciitapp.observeconnectivity.ConnectivityObserver
import com.example.rcciitapp.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val isConnectivityAvailable: Boolean = false,
    val isAdminLoggedIn: Boolean = false
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val dataStoreManager: DataStoreManager
) :
    ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {

            //delay(3000)
            _homeUiState.value =
                _homeUiState.value.copy(isLoading = false)
            observeConnectivity()
            dataStoreManager.isAdminLoggedIn().collectLatest { bool ->
                _homeUiState.value =
                    _homeUiState.value.copy(isAdminLoggedIn = bool)

            }
        }
    }


    private fun observeConnectivity() {
        connectivityObserver.connectionState
            .distinctUntilChanged()
            .map { it === ConnectionState.Available }
            .onEach { _homeUiState.value = _homeUiState.value.copy(isConnectivityAvailable = it) }
            .launchIn(viewModelScope)
    }
}