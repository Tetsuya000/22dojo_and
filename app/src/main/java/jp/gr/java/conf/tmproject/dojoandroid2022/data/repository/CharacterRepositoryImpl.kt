package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.CharacterLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val characterLocalDataSource: CharacterLocalDataSource
) : CharacterRepository {

    override fun saveCharacterName(characterName: String) = characterLocalDataSource.saveCharacterName(characterName)

    override fun loadCharacterName(): String = characterLocalDataSource.loadCharacterName()
}
