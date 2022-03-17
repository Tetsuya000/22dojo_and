package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCharacterDialogViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val characterName: MutableStateFlow<String> = MutableStateFlow("")

    private val _isEditSuccess: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isEditSuccess: SharedFlow<Boolean> = _isEditSuccess

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    fun saveCharacterName(newCharacterName: String) = viewModelScope.launch {
        runCatching {
            characterRepository.saveCharacterName(newCharacterName)
        }.onSuccess {
            _isEditSuccess.emit(true)
        }.onFailure {
            _isError.emit(true)
        }
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
