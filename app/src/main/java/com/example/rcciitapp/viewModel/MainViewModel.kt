package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.observeconnectivity.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            //delay(3000)
            _isLoading.value = false
        }
       // observeConnectivity()
    }

   /* private fun observeConnectivity() {
        connectivityObserver.observe().distinctUntilChanged()
            .map { it === ConnectivityObserver.Status.Available }
            .onEach { _homeUiState.value = _homeUiState.value.copy(isConnectivityAvailable = it) }
            .launchIn(viewModelScope)
    }*/
}