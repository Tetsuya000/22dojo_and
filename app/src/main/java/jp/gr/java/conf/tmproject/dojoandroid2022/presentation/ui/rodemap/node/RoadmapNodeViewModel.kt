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

    val currentCharacterLevel: MutableStateFlow<Int> = MutableStateFlow(1)
    val oldCharacterLevel: MutableStateFlow<Int> = MutableStateFlow(1)

    var isLevelInitialize = false

    private val _masterNodeList: MutableStateFlow<List<Node>> = MutableStateFlow(emptyList())
    val masterNodeList: StateFlow<List<Node>> = _masterNodeList

    private fun loadMasterNode() = viewModelScope.launch {
        roadmapRepository.loadMasterNode().collect {
            _masterNodeList.value = it
        }
    }

    /**
     * 「選択したノード」が「習得済みノードリスト」に存在するか
     */
    fun isMaster(selectedNodeId: Int): Boolean {
        return masterNodeList.value.any { node -> node.id == selectedNodeId }
    }

    private fun setCharacterLevel() = viewModelScope.launch {
        getCharacterLevelUseCase.getCharacterLevel().collect { level ->
            println("あああああああ")
            if (isLevelInitialize) {
                oldCharacterLevel.value = currentCharacterLevel.value
                currentCharacterLevel.value = level
            } else {
                oldCharacterLevel.value = level
                currentCharacterLevel.value = level
                isLevelInitialize = true
            }
        }
    }

    init {
        loadMasterNode()
        setCharacterLevel()
    }
}
