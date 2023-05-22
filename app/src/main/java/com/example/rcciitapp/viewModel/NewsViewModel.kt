package com.example.rcciitapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rcciitapp.data.remote.entity.NewsItem
import com.example.rcciitapp.domain.repository.NewsRepository
import com.example.rcciitapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class NewsUiState(
    val news: List<NewsItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState get() = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val res = newsRepository.getNews()
                res.collectLatest { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            /*_uiState.value =
                                _uiState.value.copy(isLoading = false, news = resource.data!!)*/
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