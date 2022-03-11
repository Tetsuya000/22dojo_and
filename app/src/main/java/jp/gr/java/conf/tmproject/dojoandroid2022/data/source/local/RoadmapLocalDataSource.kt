package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import kotlinx.coroutines.flow.Flow

interface RoadmapLocalDataSource {
    suspend fun saveNode(node: Node)
    suspend fun deleteNode(node: Node)
    suspend fun loadAllNode(): Flow<List<Node>>
}
