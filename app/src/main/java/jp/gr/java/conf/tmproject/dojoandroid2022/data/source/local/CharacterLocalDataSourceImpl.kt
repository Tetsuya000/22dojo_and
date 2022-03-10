package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.UserSetting.PREF_USER_SETTINGS_NAME
import javax.inject.Inject

class CharacterLocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : CharacterLocalDataSource {

    private val pref = context.getSharedPreferences(PREF_USER_SETTINGS_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    override fun saveCharacterName(characterName: String) {
        editor
            .putString(UserSetting.CharacterPrefKey.CHARACTER_NAME.name, characterName)
            .commit()
    }

    override fun loadCharacterName(): String {
        return pref.getString(UserSetting.CharacterPrefKey.CHARACTER_NAME.name, context.getString(R.string.character_name)) ?: throw Exception("名前が存在しません")
    }
}
