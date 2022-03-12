package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun saveCharacterName(characterName: String)
    fun loadCharacterName(): Flow<String>
}
