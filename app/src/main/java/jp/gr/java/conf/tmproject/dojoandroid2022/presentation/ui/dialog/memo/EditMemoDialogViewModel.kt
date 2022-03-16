package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.MemoRepository
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditMemoDialogViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository,
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _isSaveSuccess: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isSaveSuccess: SharedFlow<Boolean> = _isSaveSuccess

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    fun saveMemo(
        nodeId: Int,
        memo: String
    ) = viewModelScope.launch {
        runCatching {
            memoRepository.saveMemo(Memo(nodeId, memo))
        }.onSuccess {
            _isSaveSuccess.emit(true)
        }.onFailure {
            _isError.emit(true)
        }
    }
}
