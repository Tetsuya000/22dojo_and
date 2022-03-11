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

    /**
     * 名前編集に双方向データバインディングを使用すると、「画面に表示している現在の名前」が同時に描き変わるため、
     * 新しい名前はボタン押下時に代入する
     */
    fun saveCharacterName(newCharacterName: String) {
        characterRepository.saveCharacterName(newCharacterName)
        characterName.value = newCharacterName
    }

    private fun loadCharacterName() {
        characterName.value = characterRepository.loadCharacterName()
    }

    init {
        loadCharacterName()
    }
}
