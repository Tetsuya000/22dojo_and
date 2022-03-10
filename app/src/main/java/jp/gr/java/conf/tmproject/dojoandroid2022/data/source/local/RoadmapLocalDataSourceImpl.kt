package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.UserSetting.PREF_USER_SETTINGS_NAME
import javax.inject.Inject

class RoadmapLocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : RoadmapLocalDataSource {

    private val pref = context.getSharedPreferences(PREF_USER_SETTINGS_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    override fun saveNode(nodeId: Int) {
        editor
            .putBoolean(nodeId.toString(), true)
            .commit()
    }

    override fun deleteNode(nodeId: Int) {
        editor
            .remove(nodeId.toString())
            .commit()
    }

    override fun checkNodeMastery(targetNodeId: Int): Boolean = pref.getBoolean(targetNodeId.toString(), false)
}
