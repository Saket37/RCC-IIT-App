package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.Event
import com.example.rcciitapp.domain.repository.EventRepository
import com.example.rcciitapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class EventState(
    val event: List<Event> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class EventViewModel @Inject constructor(private val eventRepository: EventRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(EventState())
    val uiState get() = _uiState

    init {
        fetchEventData()
    }

    private fun fetchEventData() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val res = eventRepository.getEvents()
                res.collectLatest { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            _uiState.value =
                                _uiState.value.copy(isLoading = false, event = resource.data!!)
                        }

                        Status.ERROR -> {
                            _uiState.value =
                                _uiState.value.copy(isLoading = false, error = resource.message)

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