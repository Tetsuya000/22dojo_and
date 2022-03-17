package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api.SearchResponse
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.GithubRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _searchResult: MutableStateFlow<SearchResponse?> = MutableStateFlow(null)
    val searchResult: StateFlow<SearchResponse?> = _searchResult

    private val _errorMessage: MutableSharedFlow<String> = MutableSharedFlow()
    val errorMessage: SharedFlow<String> = _errorMessage

    fun searchRepository(query: String) = viewModelScope.launch {
        runCatching {
            githubRepository.searchRepository(query)
        }.onSuccess { response ->
            _searchResult.value = response.body()
        }.onFailure {
            _errorMessage.emit(it.message!!)
        }
    }
}
