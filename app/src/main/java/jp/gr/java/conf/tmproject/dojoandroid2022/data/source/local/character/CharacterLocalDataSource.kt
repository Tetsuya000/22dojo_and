package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.character

import kotlinx.coroutines.flow.Flow

interface CharacterLocalDataSource {
    suspend fun saveCharacterName(characterName: String)
    fun loadCharacterName(): Flow<String>
}
