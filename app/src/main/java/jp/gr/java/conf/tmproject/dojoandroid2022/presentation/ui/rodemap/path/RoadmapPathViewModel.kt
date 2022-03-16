package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Roadmap
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoadmapPathViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    private val _loadState: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.Nothing)
    val loadState: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.Nothing)

    private val _roadMap: MutableStateFlow<Roadmap?> = MutableStateFlow(null)
    val roadMap: StateFlow<Roadmap?> = _roadMap

    private fun getRodeMap() = viewModelScope.launch {
        runCatching {
            _loadState.value = LoadState.Loading
            _roadMap.value = roadmapRepository.getRoadmap()
        }.onSuccess {
            _loadState.value = LoadState.Done
        }.onFailure {
            _loadState.value = LoadState.Error
        }
    }

    init {
        getRodeMap()
    }
}
