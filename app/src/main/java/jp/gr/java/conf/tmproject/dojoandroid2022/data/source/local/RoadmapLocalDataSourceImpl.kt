package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.InputStreamReader
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.db.NodeDao
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMapModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RoadmapLocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val dao: NodeDao) : RoadmapLocalDataSource {

    override fun parseRodeMap(): RoadMapModel {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open("android-developer-roadmap2022.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonData = bufferedReader.readText()

        return Json.decodeFromString(jsonData)
    }

    override suspend fun saveNode(node: Node) = dao.insert(node.toEntity())

    override suspend fun deleteNode(node: Node) = dao.delete(node.toEntity())

    override fun loadMasterNode(): Flow<List<Node>> = dao.loadMasterNode().map { nodes ->
        nodes.map { entity ->
            entity.toDomain()
        }
    }
}
