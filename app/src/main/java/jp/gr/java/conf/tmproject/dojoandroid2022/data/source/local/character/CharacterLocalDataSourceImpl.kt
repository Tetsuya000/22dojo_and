package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.character

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterLocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val dataStore: DataStore<Preferences>) : CharacterLocalDataSource {

    override suspend fun saveCharacterName(characterName: String) {
        dataStore.edit { settings -> settings[CHARACTER_NAME] = characterName }
    }

    override fun loadCharacterName(): Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) return@catch emit(emptyPreferences())
        throw exception
    }.map { preferences ->
        preferences[CHARACTER_NAME] ?: context.getString(R.string.character_name)
    }

    companion object {
        private val CHARACTER_NAME = stringPreferencesKey("character_name")
    }
}
