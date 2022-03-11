package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoadmapNodeViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    private val nodes: MutableStateFlow<List<Node>> = MutableStateFlow(emptyList())

    fun saveNode(node: Node) = viewModelScope.launch {
        roadmapRepository.saveNode(node)
    }

    fun deleteNode(node: Node) = viewModelScope.launch {
        roadmapRepository.deleteNode(node)
    }

    private fun loadAllNode() = viewModelScope.launch {
        roadmapRepository.loadAllNode().collect {
            nodes.value = it
        }
    }

    fun checkMaster(selectedNodeId: Int): Boolean {
        val selectedNode = nodes.value.filter { node -> node.id == selectedNodeId }
        println(nodes.value)
        return selectedNode.isNotEmpty()
    }

    init {
        loadAllNode()
    }
}
