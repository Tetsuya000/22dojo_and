package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class RoadmapPathViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun parseRodeMap() = repository.parseRodeMap()
}
