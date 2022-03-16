package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.setting

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
class SettingViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val getCharacterLevelUseCase: GetCharacterLevelUseCase
) : ViewModel() {

    val characterName: MutableStateFlow<String> = MutableStateFlow("")
    val characterLevel: MutableStateFlow<String> = MutableStateFlow("1")

    private fun loadCharacterName() = viewModelScope.launch {
        characterRepository.loadCharacterName().collect { name ->
            characterName.value = name
        }
    }

    private fun setCharacterLevel() = viewModelScope.launch {
        getCharacterLevelUseCase.getCharacterLevel().collect { level ->
            characterLevel.value = level.toString()
        }
    }

    init {
        loadCharacterName()
        setCharacterLevel()
    }
}
