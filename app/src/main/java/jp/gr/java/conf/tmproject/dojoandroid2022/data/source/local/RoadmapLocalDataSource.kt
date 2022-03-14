package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.NodeEntity
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMap
import kotlinx.coroutines.flow.Flow

interface RoadmapLocalDataSource {
    fun parseRodeMap(): RoadMap
    suspend fun saveNode(node: Node)
    suspend fun deleteNode(node: Node)
    fun loadMasterNode(): Flow<List<Node>>
    suspend fun loadSelectedNode(nodeId: Int): NodeEntity
}
