package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.section

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import javax.inject.Inject

@HiltViewModel
class RoadmapSectionViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : ViewModel()
