package com.example.rcciitapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.CourseData
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.utils.DataStoreManager
import com.example.rcciitapp.utils.SharedPreferenceManager
import com.example.rcciitapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CourseUiState(
    val courses: List<CourseData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStore: DataStoreManager,
    private val sharedPreferenceManager: SharedPreferenceManager

) : ViewModel() {
    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState get() = _uiState

    init {
        fetchCourses()
    }

    private fun fetchCourses() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            //val token = dataStore.getToken().first()
            //val token = sharedPreferenceManager.getToken()
           // Log.d("TOKEN", "Bearer $token")
            repository.getCourses().collectLatest { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("C_VM", resource.data?.data.toString())
                        if (resource.data?.status == "success") {
                            resource.data.let {
                                _uiState.value =
                                    _uiState.value.copy(
                                        courses = resource.data.data,
                                        isLoading = false
                                    )
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