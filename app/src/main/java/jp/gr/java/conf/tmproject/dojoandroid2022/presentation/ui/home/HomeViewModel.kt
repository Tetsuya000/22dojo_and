package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    fun saveCharacterName(characterName: String) = characterRepository.saveCharacterName(characterName)

    fun loadCharacterName(): String = characterRepository.loadCharacterName()
}
