package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.MemoRepository
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteNodeDialogViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository,
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _isDeleteSuccess: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isDeleteSuccess: SharedFlow<Boolean> = _isDeleteSuccess

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    fun deleteNode(node: Node?) = viewModelScope.launch {
        if (node == null) return@launch

        runCatching {
            roadmapRepository.deleteNode(node)
            memoRepository.deleteMemo(node.id)
        }.onSuccess {
            _isDeleteSuccess.emit(true)
        }.onFailure {
            _isError.emit(true)
        }
    }
}
