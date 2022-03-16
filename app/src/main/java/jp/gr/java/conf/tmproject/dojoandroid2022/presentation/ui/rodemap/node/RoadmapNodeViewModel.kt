package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor.GetCharacterLevelUseCase
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoadmapNodeViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository,
    private val getCharacterLevelUseCase: GetCharacterLevelUseCase
) : ViewModel() {

    private val _masterNodeList: MutableStateFlow<List<Node>> = MutableStateFlow(emptyList())
    val masterNodeList: StateFlow<List<Node>> = _masterNodeList

    private val _isLevelUp: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isLevelUp: StateFlow<Boolean?> = _isLevelUp

    private val oldCharacterLevel: MutableStateFlow<Int> = MutableStateFlow(-1)

    private fun updateCharacterLevel() = viewModelScope.launch {
        getCharacterLevelUseCase.getCharacterLevel().collect { latestCharacterLevel ->
            val isInitialize = oldCharacterLevel.value != -1
            val noChangeLevel = oldCharacterLevel.value == latestCharacterLevel
            if (!isInitialize || noChangeLevel) {
                oldCharacterLevel.value = latestCharacterLevel
                return@collect
            }

            _isLevelUp.emit(oldCharacterLevel.value < latestCharacterLevel)
            oldCharacterLevel.value = latestCharacterLevel
        }
    }

    fun clearLevel() {
        _isLevelUp.value = null
    }

    private fun loadMasterNode() = viewModelScope.launch {
        roadmapRepository.loadMasterNode().collect { list ->
            _masterNodeList.value = list
        }
    }

    /**
     * 「選択したノード」が「習得済みノードリスト」に存在するか
     */
    fun isMaster(selectedNodeId: Int): Boolean {
        return masterNodeList.value.any { node -> node.id == selectedNodeId }
    }

    init {
        updateCharacterLevel()
        loadMasterNode()
    }
}
