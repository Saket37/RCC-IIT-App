package com.example.rcciitapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.DeleteFacultyResponse
import com.example.rcciitapp.data.remote.entity.EditFacultyBody
import com.example.rcciitapp.data.remote.entity.Faculty
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.utils.FacultyEvent
import com.example.rcciitapp.utils.FacultyUpdateEvent
import com.example.rcciitapp.utils.Status
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

data class DeleteFacultyUiState(
    val faculty: DeleteFacultyResponse? = null,
    val deleted: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class FacultyScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow(FacultyScreenUiState())
    val uiState get() = _uiState

    private val _editUiState = MutableStateFlow(EditFacultyUiState())
    val editUiState get() = _editUiState
    private val edit = mutableListOf<Faculty>()

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

    fun fetchEditFacultyData(id: String) {
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