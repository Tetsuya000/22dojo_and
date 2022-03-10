package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import javax.inject.Inject

@HiltViewModel
class RoadmapNodeViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    fun saveNode(nodeId: Int) = roadmapRepository.saveNode(nodeId)

    fun deleteNode(nodeId: Int) = roadmapRepository.deleteNode(nodeId)

    fun checkNodeMastery(targetNodeId: Int): Boolean = roadmapRepository.checkNodeMastery(targetNodeId)
}
