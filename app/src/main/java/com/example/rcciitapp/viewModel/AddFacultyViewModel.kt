package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.viewModel.event.AddFacultyEvent
import com.example.rcciitapp.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddFacultyUiState(
    val name: String? = null,
    val email: String? = null,
    val doj: String? = null,
    val degree: String? = null,
    val designation: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class AddFacultyViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddFacultyUiState())
    val uiState get() = _uiState

    private fun updateEmail(email: String) {
        uiState.value = uiState.value.copy(email = email)
    }

    private fun updateName(name: String) {
        uiState.value = uiState.value.copy(name = name)
    }

    private fun updateDegree(degree: String) {
        uiState.value = uiState.value.copy(degree = degree)
    }

    private fun updateDoj(doj: String) {
        uiState.value = uiState.value.copy(doj = doj)
    }

    private fun updateDesignation(designation: String) {
        uiState.value = uiState.value.copy(designation = designation)
    }

    private fun dismissError() {
        uiState.value = uiState.value.copy(error = null)
    }

    fun submit() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {

        }
    }

    fun handleEditEvent(event: AddFacultyEvent) {
        when (event) {
            is AddFacultyEvent.NameChanged -> {
                updateName(event.name)
            }

            is AddFacultyEvent.DegreeChanged -> {
                updateDegree(event.degree)
            }

            is AddFacultyEvent.DesignationChanged -> {
                updateDesignation(event.designation)
            }

            is AddFacultyEvent.DojChanged -> {
                updateDoj(event.doj)
            }

            is AddFacultyEvent.EmailChanged -> {
                updateEmail(event.email)
            }
            /*AddFacultyEvent.Update -> {
                patchUpdateFaculty()
            }*/

        }
    }
}