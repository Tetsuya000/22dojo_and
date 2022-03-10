package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.setting

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val characterName: MutableStateFlow<String> = MutableStateFlow("")

    fun loadCharacterName() {
        characterName.value = characterRepository.loadCharacterName()
    }
}
