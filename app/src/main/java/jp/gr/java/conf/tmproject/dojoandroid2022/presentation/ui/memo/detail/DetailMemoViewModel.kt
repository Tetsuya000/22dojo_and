package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.memo.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.MemoRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMemoViewModel @Inject constructor(private val memoRepository: MemoRepository) : ViewModel() {

    val title: MutableStateFlow<String> = MutableStateFlow("")
    val memo: MutableStateFlow<String> = MutableStateFlow("")

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    fun loadMemoById(nodeId: Int) = viewModelScope.launch {
        memoRepository.loadMemoById(nodeId).collect { list ->
            list.forEach {
                title.value = it.title
                memo.value = it.memo
            }
        }
    }
}
