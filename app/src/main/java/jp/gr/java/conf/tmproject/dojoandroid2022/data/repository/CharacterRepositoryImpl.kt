package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.CharacterLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterLocalDataSource: CharacterLocalDataSource
) : CharacterRepository {

    override fun saveCharacterName(characterName: String) = characterLocalDataSource.saveCharacterName(characterName)

    override fun loadCharacterName(): String = characterLocalDataSource.loadCharacterName()
}
