package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor.GetCharacterLevelUseCase
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val roadmapRepository: RoadmapRepository,
    private val getCharacterLevelUseCase: GetCharacterLevelUseCase
) : ViewModel() {

    val characterName: MutableStateFlow<String> = MutableStateFlow("")
    val characterLevel: MutableStateFlow<String> = MutableStateFlow("1")

    fun loadCharacterName() {
        characterName.value = characterRepository.loadCharacterName()
    }

    fun loadCharacterLevel() {
        characterLevel.value = getCharacterLevelUseCase.getCharacterLevel().toString()
    }

    private fun loadAllNode() = viewModelScope.launch(Dispatchers.IO) {
        roadmapRepository.loadAllNode().collect {
            it.forEach {
//                println(it.title)
            }
            characterLevel.value = ((it.size / 2) + 1).toString()
        }
    }

    init {
        loadAllNode()
    }
}
