package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import javax.inject.Inject

@HiltViewModel
class RoadmapPathViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    fun parseRodeMap() = roadmapRepository.parseRodeMap()
}
