package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.Faculty
import com.example.rcciitapp.domain.repository.Repository
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

@HiltViewModel
class FacultyScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow(FacultyScreenUiState())
    val uiState get() = _uiState

    init {
        fetch()
    }

    private fun fetch() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            repository.getFaculty("CSE").collectLatest { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data?.status == "success") {
                            resource.data.let {
                                _uiState.value =
                                    _uiState.value.copy(faculty = it.data, isLoading = false)
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
}