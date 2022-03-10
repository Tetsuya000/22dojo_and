package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

interface CharacterRepository {
    fun saveCharacterName(characterName: String)
    fun loadCharacterName(): String
}
