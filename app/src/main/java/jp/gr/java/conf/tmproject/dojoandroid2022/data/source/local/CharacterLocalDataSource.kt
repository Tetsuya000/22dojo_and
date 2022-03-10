package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

interface CharacterLocalDataSource {
    fun saveCharacterName(characterName: String)
    fun loadCharacterName(): String
}
