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
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    private val _masterNodeList: MutableStateFlow<List<Node>> = MutableStateFlow(emptyList())
    val masterNodeList: StateFlow<List<Node>> = _masterNodeList

    fun saveNode(node: Node) = viewModelScope.launch {
        roadmapRepository.saveNode(node)
    }

    fun deleteNode(node: Node) = viewModelScope.launch {
        roadmapRepository.deleteNode(node)
    }

    private fun loadAllNode() = viewModelScope.launch {
        roadmapRepository.loadAllNode().collect {
            _masterNodeList.value = it
        }
    }

    fun checkMaster(selectedNodeId: Int): Boolean {
        val selectedNode = masterNodeList.value.filter { node -> node.id == selectedNodeId }
        return selectedNode.isNotEmpty()
    }

    init {
        loadAllNode()
    }
}
