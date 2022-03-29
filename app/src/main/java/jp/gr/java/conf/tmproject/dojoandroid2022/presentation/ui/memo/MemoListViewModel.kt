package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.MemoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoListViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _memoList: MutableStateFlow<List<Memo>> = MutableStateFlow(emptyList())
    val memoList: StateFlow<List<Memo>> = _memoList

    private fun loadAllMemo() = viewModelScope.launch {
        val filterMemoList = mutableListOf<Memo>()

        memoRepository.loadAllMemo().collect {
            // 保持されているため初期化する
            filterMemoList.clear()
            it.forEach { memo ->
                if (memo.memo != "") filterMemoList.add(memo)
            }
            _memoList.value = filterMemoList
        }
    }

    init {
        loadAllMemo()
    }
}
