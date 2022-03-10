package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import javax.inject.Inject

class CharacterLocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : CharacterLocalDataSource {

    private val pref = context.getSharedPreferences(PREF_CHARACTER_DATA, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    override fun saveCharacterName(characterName: String) {
        editor
            .putString(CharacterPrefKey.CHARACTER_NAME.name, characterName)
            .commit()
    }

    override fun loadCharacterName(): String {
        return pref
            .getString(CharacterPrefKey.CHARACTER_NAME.name, context.getString(R.string.character_name))
            .toString()
    }

    companion object {
        const val PREF_CHARACTER_DATA = "character_data"
    }

    private enum class CharacterPrefKey {
        CHARACTER_NAME
    }
}
