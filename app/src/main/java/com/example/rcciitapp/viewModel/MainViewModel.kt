package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.observeconnectivity.ConnectionState
import com.example.rcciitapp.observeconnectivity.ConnectivityObserver
import com.example.rcciitapp.utils.DataStoreManager
import com.example.rcciitapp.utils.LogoutEvent
import com.example.rcciitapp.utils.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    private val dataStoreManager: DataStoreManager,
    private val sharedPreferenceManager: SharedPreferenceManager

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
            val isAdminLoggedIn =sharedPreferenceManager.isAdminLoggedIn()
            _homeUiState.value =
                _homeUiState.value.copy(isAdminLoggedIn = isAdminLoggedIn)
            /*dataStoreManager.isAdminLoggedIn().collectLatest { bool ->
                _homeUiState.value =
                    _homeUiState.value.copy(isAdminLoggedIn = bool)

            }*/
        }
    }

    private fun logout() {
        viewModelScope.launch {
            dataStoreManager.deleteUserData()
            _homeUiState.value =
                _homeUiState.value.copy(isAdminLoggedIn = false)
        }
    }

    private fun observeConnectivity() {
        connectivityObserver.connectionState
            .distinctUntilChanged()
            .map { it === ConnectionState.Available }
            .onEach { _homeUiState.value = _homeUiState.value.copy(isConnectivityAvailable = it) }
            .launchIn(viewModelScope)
    }

    fun handleEvent(logoutEvent: LogoutEvent) {
        when (logoutEvent) {
            is LogoutEvent.Logout -> {
                logout()
            }
        }

    }
}