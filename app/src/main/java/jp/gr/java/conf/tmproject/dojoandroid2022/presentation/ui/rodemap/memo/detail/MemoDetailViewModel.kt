package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.memo.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoDetailViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    val memo: MutableStateFlow<String> = MutableStateFlow("")

    private val _isDeleteSuccess: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isDeleteSuccess: SharedFlow<Boolean> = _isDeleteSuccess

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    fun setSelectedNodeMemo(nodeId: Int) = viewModelScope.launch {
        memo.value = roadmapRepository.loadSelectedNode(nodeId).toDomain().memo
    }

    fun deleteNode(node: Node) = viewModelScope.launch {
        runCatching {
            roadmapRepository.deleteNode(node)
        }.onSuccess {
            _isDeleteSuccess.emit(true)
        }.onFailure {
            _isError.emit(true)
        }
    }
}
