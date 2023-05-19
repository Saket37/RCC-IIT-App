package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.utils.DataStoreManager
import com.example.rcciitapp.utils.FacultyUpdateEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

data class FacultyUiState(
    val name: String? = null,
    val email: String? = null,
    val doj: String? = null,
    val degree: String? = null,
    val designation: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class FacultyViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(FacultyUiState())
    val uiState get() = _uiState

    private fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    private fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    private fun updateDegree(degree: String) {
        _uiState.value = _uiState.value.copy(degree = degree)
    }

    private fun updateDoj(doj: String) {
        _uiState.value = _uiState.value.copy(doj = doj)
    }

    private fun updateDesignation(designation: String) {
        _uiState.value = _uiState.value.copy(designation = designation)
    }

    private fun dismissError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun handleEvent(event: FacultyUpdateEvent) {
        when (event) {
            is FacultyUpdateEvent.NameChanged -> {
                updateName(event.name)
            }

            is FacultyUpdateEvent.DegreeChanged -> {
                updateDegree(event.degree)
            }

            is FacultyUpdateEvent.DesignationChanged -> {
                updateDesignation(event.designation)
            }

            is FacultyUpdateEvent.DojChanged -> {
                updateDoj(event.doj)
            }

            is FacultyUpdateEvent.EmailChanged -> {
                updateEmail(event.email)
            }
        }
    }
}