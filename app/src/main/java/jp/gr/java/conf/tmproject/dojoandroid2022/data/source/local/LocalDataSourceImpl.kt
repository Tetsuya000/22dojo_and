package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : LocalDataSource {

    private val pref = context.getSharedPreferences(PREF_USER_SETTINGS_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    override fun saveCharacterName(characterName: String) {
        editor
            .putString(UserSettingsPrefKey.CHARACTER_NAME.name, characterName)
            .commit()
    }

    override fun loadCharacterName(): String = pref
        .getString(UserSettingsPrefKey.CHARACTER_NAME.name, context.getString(R.string.character_name))
        .toString()

    companion object {
        const val PREF_USER_SETTINGS_NAME = "jp.gr.java.conf.tmproject.dojoandroid2022.user_settings"
    }

    private enum class UserSettingsPrefKey {
        CHARACTER_NAME
    }
}
