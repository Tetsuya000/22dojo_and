package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

interface Repository {
    fun saveCharacterName(characterName: String)
    fun loadCharacterName(): String
}
