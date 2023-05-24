package com.example.rcciitapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.DeleteFacultyResponse
import com.example.rcciitapp.data.remote.entity.EditFacultyBody
import com.example.rcciitapp.data.remote.entity.Faculty
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FacultyScreenUiState(
    val faculty: List<Faculty> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,

    )


data class DeleteFacultyUiState(
    val faculty: DeleteFacultyResponse? = null,
    val deleted: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class FacultyScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager,
    private val sharedPreferenceManager: SharedPreferenceManager

) : ViewModel() {
    private val _uiState = MutableStateFlow(FacultyScreenUiState())
    val uiState get() = _uiState
    private val _isAdminLoggedIn = MutableStateFlow(false)
    val isAdminLoggedIn get() = _isAdminLoggedIn

    private val edit = mutableListOf<Faculty>()

    init {
        _isAdminLoggedIn.value = sharedPreferenceManager.isAdminLoggedIn()

    }

    private val _deleteUiState = MutableStateFlow(DeleteFacultyUiState())
    fun fetch(stream: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            repository.getFaculty(stream = stream).collectLatest { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data?.status == "success") {
                            resource.data.let {
                                _uiState.value =
                                    _uiState.value.copy(faculty = it.data, isLoading = false)
                                edit.clear()

                                edit.addAll(it.data)
                                Log.d("EDIT", edit.toString())
                            }
                        }
                    }
                    Status.ERROR -> {
                        if (resource.data?.status == "fail") {
                            _uiState.value =
                                _uiState.value.copy(
                                    isLoading = false,
                                    error = resource.data.message
                                )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = resource.message
                            )
                        }
                    }
                    Status.LOADING -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }

            }
        }
    }

    private fun deleteFaculty(id: String) {
        viewModelScope.launch {

            repository.deleteFaculty(id = id).collectLatest { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data?.status == "success") {
                            resource.data.let {
                                val course = _uiState.value.faculty.find { it._id == id }
                                if (course != null) {
                                    _uiState.value = _uiState.value.copy(
                                        faculty = _uiState.value.faculty.toMutableList()
                                            .apply { remove(course) }
                                    )
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        if (resource.data?.status == "fail") {
                            _uiState.value =
                                _uiState.value.copy(
                                    isLoading = false,
                                    error = resource.data.message
                                )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = resource.message
                            )
                        }
                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    fun handleAuthEvent(event: FacultyEvent) {
        when (event) {
            is FacultyEvent.DeleteFaculty -> deleteFaculty(event.id)
        }
    }



    /*fun fetchEditFacultyData(id: String) {
        Log.d("EDIT_LIST", edit.toString())
        Log.d("VM_ID", id)
        val faculty =
            edit.find { it._id.toString() == id } // Convert id to the same type as _id
        Log.d("Find", faculty.toString())
        if (faculty != null) {
            _editUiState.value = _editUiState.value.copy(
                id = faculty._id,
                name = faculty.name,
                email = faculty.email,
                doj = faculty.dob,
                degree = faculty.degree,
                designation = faculty.designation
            )
        }

    }
*/


}