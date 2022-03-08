package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.model.RoadMapModel

interface Repository {
    fun parseRodeMap(): RoadMapModel
    fun saveCharacterName(characterName: String)
    fun loadCharacterName(): String
}
