package com.example.rcciitapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.Login
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.model.AdminAuthState
import com.example.rcciitapp.utils.AdminAuthEvent
import com.example.rcciitapp.utils.DataStoreManager
import com.example.rcciitapp.utils.SharedPreferenceManager
import com.example.rcciitapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStore: DataStoreManager,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {
    private val _authUiState = MutableStateFlow(AdminAuthState())
    val authUiState get() = _authUiState
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn get() = _isLoggedIn
    /* private fun toggleAuthenticationMode() {
         val authenticationMode = _authUiState.value.authenticationMode
         val newAuthMode = if (authenticationMode == AuthenticationMode.SIGN_IN) {
             AuthenticationMode.SIGN_UP
         } else {
             AuthenticationMode.SIGN_IN
         }
         _authUiState.value = _authUiState.value.copy(authenticationMode = newAuthMode)
     }*/

    private fun updateEmail(email: String) {
        _authUiState.value = _authUiState.value.copy(email = email)
    }

    private fun authenticate() {
        _authUiState.value = _authUiState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            //delay(2000)
            withContext(Dispatchers.Main) {
                val login = _authUiState.value.email?.let { email ->
                    _authUiState.value.password?.let { password ->
                        Login(
                            email,
                            password
                        )
                    }
                }
                //val login = Login(_authUiState.value.email!!,_authUiState.value.password!!)
                Log.d("LOGIN_VALUES", login.toString())
                if (login != null) {
                    repository.adminLogin(login).collectLatest { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                if (resource.data?.status == "success") {
                                    Log.d("LOGIN", resource.data.toString())
                                    resource.data.let {
                                        dataStore.saveUserData(
                                            "Bearer $it.token!!",
                                            it.user!!.email, it.user.name, it.user._id
                                        )
                                        sharedPreferenceManager.saveUserData(
                                            it.token!!,
                                            it.user.email,
                                            it.user.name,
                                            it.user._id
                                        )
                                        _isLoggedIn.value = true
                                        _authUiState.value =
                                            _authUiState.value.copy(isLoading = false)
                                    }
                                }
                            }

                            Status.ERROR -> {
                                if (resource.data?.status == "fail") {
                                    _authUiState.value =
                                        _authUiState.value.copy(
                                            isLoading = false,
                                            error = resource.data.message
                                        )
                                } else {
                                    _authUiState.value = _authUiState.value.copy(
                                        isLoading = false,
                                        error = resource.message
                                    )
                                }
                            }

                            Status.LOADING -> {
                                _authUiState.value = _authUiState.value.copy(isLoading = true)
                            }
                        }
                    }
                }

            }
        }
    }

    private fun dismissError() {
        _authUiState.value = _authUiState.value.copy(error = null)
    }

    private fun updatePassword(password: String) {
        /*val requirements = mutableListOf<PasswordRequirements>()
        if (password.length > 7) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }
        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }*/

        _authUiState.value =
            _authUiState.value.copy(password = password)
    }

    fun handleAuthEvent(authEvent: AdminAuthEvent) {
        when (authEvent) {
            /*is AdminAuthEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }*/
            is AdminAuthEvent.EmailChanged -> {
                updateEmail(authEvent.email)
            }

            is AdminAuthEvent.PasswordChanged -> {
                updatePassword(authEvent.password)
            }

            is AdminAuthEvent.ErrorDismissed -> {
                dismissError()
            }

            is AdminAuthEvent.Authenticate -> {
                authenticate()
            }
        }
    }
}