package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.R
import com.example.rcciitapp.data.Result
import com.example.rcciitapp.data.course.impl.FakeCourseRepository
import com.example.rcciitapp.model.Course
import com.example.rcciitapp.utils.DataStoreManager
import com.example.rcciitapp.utils.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class CourseState(
    val course: List<Course> = emptyList(),
    val isSelectedCourse: Course? = null,
    val isCourseOpen: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val courseRepository: FakeCourseRepository,
    private val dataStoreManager: DataStoreManager
) :
    ViewModel() {
    private val _courseUiState = MutableStateFlow(CourseState())
    val courseUiState get() = _courseUiState
    private val _isAdminLoggedIn = MutableStateFlow(false)
    val isAdminLoggedIn get() = _isAdminLoggedIn

    init {
        viewModelScope.launch {
            dataStoreManager.isAdminLoggedIn().collectLatest { bool ->
                _isAdminLoggedIn.update {
                    bool
                }
            }
        }

        observeData()
    }

    private fun observeData() {
        _courseUiState.value = _courseUiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val result = courseRepository.getAllCourses()
            _courseUiState.update {
                when (result) {
                    is Result.Success -> it.copy(
                        course = result.data,
                        isLoading = false
                    )

                    is Result.Error -> {
                        val errorMessage = it.error + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            messageId = R.string.load_error
                        )
                        it.copy(error = errorMessage, isLoading = false)
                    }
                }
            }
        }
    }

    /**
     * Selects the given course to view more information about it.
     */
    fun selectCourse(courseId: String) {
        val course = _courseUiState.value.course.find { it.id == courseId }
        _courseUiState.value =
            _courseUiState.value.copy(isSelectedCourse = course, isCourseOpen = true)
        // Treat selecting a detail as simply interacting with it
        //interactedWithCourseDetails(course)
    }

    /**
     * Notify that the user interacted with the course details
     */
    fun interactedWithCourseDetails(CourseId: Course) {
        _courseUiState.update {
            it.copy(
                isSelectedCourse = CourseId,
                isCourseOpen = true
            )
        }
    }

}