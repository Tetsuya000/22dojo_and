package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RoadmapLocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : RoadmapLocalDataSource {

    private val pref = context.getSharedPreferences(PREF_NODE_DATA, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    override fun saveNode(nodeId: Int) {
        editor
            .putBoolean(nodeId.toString(), false)
            .commit()
    }

    override fun deleteNode(nodeId: Int) {
        editor
            .remove(nodeId.toString())
            .commit()
    }

    override fun getMasteryNodeId(): List<String> {
        val masteryNodeMap = pref.all.filter {
            it.value == true
        }
        val masteryNodeIdList = mutableListOf<String>()
        masteryNodeMap.forEach {
            masteryNodeIdList.add(it.key)
        }
        return masteryNodeIdList
    }

    override fun checkNodeMastery(targetNodeId: Int): Boolean = pref.getBoolean(targetNodeId.toString(), false)

    companion object {
        const val PREF_NODE_DATA = "node_data"
    }
}
