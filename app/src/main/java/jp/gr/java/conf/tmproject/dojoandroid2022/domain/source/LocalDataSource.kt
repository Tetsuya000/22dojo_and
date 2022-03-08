package jp.gr.java.conf.tmproject.dojoandroid2022.domain.source

interface LocalDataSource {
    fun saveCharacterName(characterName: String)
    fun loadCharacterName(): String
}
