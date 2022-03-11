package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor.GetCharacterLevelUseCase
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val getCharacterLevelUseCase: GetCharacterLevelUseCase
) : ViewModel() {

    val characterName: MutableStateFlow<String> = MutableStateFlow("")
    val characterLevel: MutableStateFlow<String> = MutableStateFlow("")

    fun saveCharacterName(characterName: String) {
        characterRepository.saveCharacterName(characterName)
    }

    fun loadCharacterName() {
        characterName.value = characterRepository.loadCharacterName()
    }

    fun loadCharacterLevel() {
        characterLevel.value = getCharacterLevelUseCase.getCharacterLevel().toString()
    }

    init {
        loadCharacterLevel()
    }
}
