package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository) : ViewModel() {

    fun saveCharacterName(characterName: String) = repository.saveCharacterName(characterName)

    fun loadCharacterName(): String = repository.loadCharacterName()
}
