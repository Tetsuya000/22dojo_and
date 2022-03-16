package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NodeEditViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository) : ViewModel() {

    private val _isSaveSuccess: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isSaveSuccess: SharedFlow<Boolean> = _isSaveSuccess

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    fun saveNode(
        node: Node,
        memo: String) = viewModelScope.launch {

        runCatching {
            val editedNode = node.editMemo(memo)
            roadmapRepository.saveNode(editedNode)
        }.onSuccess {
            _isSaveSuccess.emit(true)
        }.onFailure {
            _isError.emit(true)
        }
    }
}
