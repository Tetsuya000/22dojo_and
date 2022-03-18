package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Roadmap
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoadmapPathViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    private val _loadState: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.Nothing)
    val loadState: MutableStateFlow<LoadState> = _loadState

    private val _roadMap: MutableStateFlow<Roadmap?> = MutableStateFlow(null)
    val roadMap: StateFlow<Roadmap?> = _roadMap

    private val _masterNodeList: MutableStateFlow<List<Node>> = MutableStateFlow(emptyList())
    val masterNodeList: StateFlow<List<Node>> = _masterNodeList

    private fun getRodeMap() = viewModelScope.launch {
        runCatching {
            _loadState.value = LoadState.Loading
            roadmapRepository.getRoadmap()
        }.onSuccess {
            _roadMap.value = it
            _loadState.value = LoadState.Done
        }.onFailure {
            _loadState.value = LoadState.Error
        }
    }

    private fun loadAllNode() = viewModelScope.launch {
        roadmapRepository.loadAllNode().collect { list ->
            _masterNodeList.value = list
        }
    }

    init {
        getRodeMap()
        loadAllNode()
    }
}
