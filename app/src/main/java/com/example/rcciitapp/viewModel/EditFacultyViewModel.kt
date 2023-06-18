package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.EditFacultyBody
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.utils.DataStoreManager
import com.example.rcciitapp.viewModel.event.FacultyUpdateEvent
import com.example.rcciitapp.utils.SharedPreferenceManager
import com.example.rcciitapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EditFacultyUiState(
    val id: String? = null,
    var name: String? = null,
    var email: String? = null,
    var doj: String? = null,
    var degree: String? = null,
    var designation: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class EditFacultyViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {
    private val _editUiState = MutableStateFlow(EditFacultyUiState())
    val editUiState get() = _editUiState

    private fun updateEmail(email: String) {
        _editUiState.value = _editUiState.value.copy(email = email)
    }

    private fun updateName(name: String) {
        _editUiState.value = _editUiState.value.copy(name = name)
    }

    private fun updateDegree(degree: String) {
        _editUiState.value = _editUiState.value.copy(degree = degree)
    }

    private fun updateDoj(doj: String) {
        _editUiState.value = _editUiState.value.copy(doj = doj)
    }

    private fun updateDesignation(designation: String) {
        _editUiState.value = _editUiState.value.copy(designation = designation)
    }

    private fun dismissError() {
        _editUiState.value = _editUiState.value.copy(error = null)
    }
    fun patchUpdateFaculty() {
        _editUiState.value = _editUiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val data = _editUiState.value.name?.let { name ->
                _editUiState.value.email?.let { email ->
                    _editUiState.value.degree?.let { degree ->
                        _editUiState.value.designation?.let { designation ->
                            _editUiState.value.doj?.let { dob ->
                                EditFacultyBody(
                                    name = name,
                                    email = email,
                                    degree = degree,
                                    designation = designation,
                                    dob = dob,
                                )
                            }
                        }
                    }
                }
            }
            if (data != null) {
                repository.editFaculty(data).collectLatest { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            if (resource.data?.status == "success") {
                                resource.data.let {
                                    _editUiState.value = _editUiState.value.copy(isLoading = false)
                                }
                            }
                        }
                        Status.ERROR -> {
                            if (resource.data?.status == "fail") {
                                _editUiState.value =
                                    _editUiState.value.copy(
                                        isLoading = false,
                                        error = resource.data.message
                                    )
                            } else {
                                _editUiState.value = _editUiState.value.copy(
                                    isLoading = false,
                                    error = resource.message
                                )
                            }
                        }
                        Status.LOADING -> {
                            _editUiState.value = _editUiState.value.copy(isLoading = true)
                        }
                    }

                }
            }
        }
    }
    fun handleEditEvent(event: FacultyUpdateEvent) {
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
            /*FacultyUpdateEvent.Update -> {
                patchUpdateFaculty()
            }*/

        }
    }
}