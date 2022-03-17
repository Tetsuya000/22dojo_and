package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.NodeEntity
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap.db.NodeDao
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Roadmap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class RoadmapLocalDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val nodeDao: NodeDao
) : RoadmapLocalDataSource {

    override fun getRoadmap(): Roadmap {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open("android-developer-roadmap2022.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonData = bufferedReader.readText()

        return Json.decodeFromString(jsonData)
    }

    override suspend fun saveNode(node: Node) = nodeDao.insert(node.toEntity())

    override suspend fun deleteNode(node: Node) = nodeDao.delete(node.toEntity())

    override fun loadAllNode(): Flow<List<Node>> = nodeDao.loadAllNode().map { nodes ->
        nodes.map { entity ->
            entity.toDomain()
        }
    }

    override suspend fun loadNodeById(id: Int): NodeEntity = nodeDao.loadNodeById(id)
}
