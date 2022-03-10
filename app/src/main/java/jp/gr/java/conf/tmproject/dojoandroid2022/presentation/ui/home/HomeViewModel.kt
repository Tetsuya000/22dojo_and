package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor.GetCharacterLevelUseCase
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val getCharacterLevelUseCase: GetCharacterLevelUseCase
) : ViewModel() {

    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> = _name

    fun saveCharacterName(characterName: String) {
        characterRepository.saveCharacterName(characterName)
    }

    fun loadCharacterName(): String = characterRepository.loadCharacterName()

    init {
        println(loadCharacterName())
        _name.value = loadCharacterName()
    }
}
