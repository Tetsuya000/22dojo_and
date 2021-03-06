package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap

import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.NodeEntity
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Roadmap
import kotlinx.coroutines.flow.Flow

interface RoadmapLocalDataSource {
    fun getRoadmap(): Roadmap
    suspend fun saveNode(node: Node)
    suspend fun deleteNode(node: Node)
    fun loadAllNode(): Flow<List<Node>>
    suspend fun loadNodeById(id: Int): NodeEntity
}
