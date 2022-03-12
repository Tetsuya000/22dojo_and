package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMapModel
import kotlinx.coroutines.flow.Flow

interface RoadmapLocalDataSource {
    fun parseRodeMap(): RoadMapModel
    suspend fun saveNode(node: Node)
    suspend fun deleteNode(node: Node)
    fun loadMasterNode(): Flow<List<Node>>
}
