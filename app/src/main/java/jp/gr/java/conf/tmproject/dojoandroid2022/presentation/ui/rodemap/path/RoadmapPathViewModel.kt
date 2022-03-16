package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoadmapPathViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : ViewModel() {

    fun parseRodeMap() = roadmapRepository.parseRodeMap()

    init {
        viewModelScope.launch {
            roadmapRepository.getRodeMap().also { response ->
                if (response.isSuccessful) {
                    println(response.body())
                } else {
                    val error = response.errorBody()!!.toString()
                }
            }
        }
    }
}
