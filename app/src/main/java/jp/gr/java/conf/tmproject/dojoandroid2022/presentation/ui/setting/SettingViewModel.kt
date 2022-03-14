package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val characterName: MutableStateFlow<String> = MutableStateFlow("")

    fun saveCharacterName(newCharacterName: String) = viewModelScope.launch {
        characterRepository.saveCharacterName(newCharacterName)
    }

    private fun loadCharacterName() = viewModelScope.launch {
        characterRepository.loadCharacterName().collect { name ->
            characterName.value = name
        }
    }

    init {
        loadCharacterName()
    }
}
