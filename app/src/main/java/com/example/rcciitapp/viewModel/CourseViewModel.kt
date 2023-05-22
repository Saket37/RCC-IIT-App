package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.Data
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.utils.DataStoreManager
import com.example.rcciitapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class CourseUiState(
    val courses: List<Data> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStore: DataStoreManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState get() = _uiState
    private val token = MutableStateFlow("")

    init {
        fetchCourses()
    }
    private fun fetchCourses() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val token = dataStore.getToken().first()
            token?.let { repository.getCourses(it) }?.collectLatest { resource ->
                withContext(Dispatchers.Main) {
                    when (resource.status) {
                        Status.SUCCESS -> {
                            if (resource.data?.status == "success") {
                                resource.data.let {
                                    _uiState.value =
                                        _uiState.value.copy(courses = resource.data.data)
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


}