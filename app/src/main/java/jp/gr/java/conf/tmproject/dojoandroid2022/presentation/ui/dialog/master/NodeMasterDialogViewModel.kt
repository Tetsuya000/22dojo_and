package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog.master

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
class NodeMasterDialogViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository) : ViewModel() {

    private val _isSaveSuccess: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isSaveSuccess: SharedFlow<Boolean> = _isSaveSuccess

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    fun saveNode(
        node: Node?) = viewModelScope.launch {
        if (node == null) return@launch

        runCatching {
            roadmapRepository.saveNode(node)
        }.onSuccess {
            _isSaveSuccess.emit(true)
        }.onFailure {
            _isError.emit(true)
        }
    }
}
