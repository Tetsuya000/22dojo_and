package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor.GetCharacterLevelUseCase
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val getCharacterLevelUseCase: GetCharacterLevelUseCase) : ViewModel() {

    val characterLevel: MutableStateFlow<String> = MutableStateFlow("1")
    val characterName: MutableStateFlow<String> = MutableStateFlow("")

    private fun setCharacterLevel() = viewModelScope.launch {
        getCharacterLevelUseCase.getCharacterLevel().collect { level ->
            characterLevel.value = level.toString()
        }
    }

    private fun loadCharacterName() = viewModelScope.launch {
        characterRepository.loadCharacterName().collect { name ->
            characterName.value = name
        }
    }

    init {
        setCharacterLevel()
        loadCharacterName()
    }
}
