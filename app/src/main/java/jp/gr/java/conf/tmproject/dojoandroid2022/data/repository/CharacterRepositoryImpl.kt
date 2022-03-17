package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.character.CharacterLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterLocalDataSource: CharacterLocalDataSource) : CharacterRepository {

    override suspend fun saveCharacterName(characterName: String) =
        characterLocalDataSource.saveCharacterName(characterName)

    override fun loadCharacterName(): Flow<String> = characterLocalDataSource.loadCharacterName()
}
