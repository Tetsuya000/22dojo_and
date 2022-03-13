package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoadmapNodeViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository) : ViewModel() {

    private val _masterNodeList: MutableStateFlow<List<Node>> = MutableStateFlow(emptyList())
    val masterNodeList: StateFlow<List<Node>> = _masterNodeList

    fun saveNode(node: Node) = viewModelScope.launch {
        roadmapRepository.saveNode(node)
    }

    fun deleteNode(node: Node) = viewModelScope.launch {
        roadmapRepository.deleteNode(node)
    }

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

    init {
        loadMasterNode()
    }
}
