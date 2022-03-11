package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMapModel
import kotlinx.coroutines.flow.Flow

interface RoadmapRepository {
    fun parseRodeMap(): RoadMapModel
    suspend fun saveNode(node: Node)
    suspend fun deleteNode(node: Node)
    suspend fun loadAllNode(): Flow<List<Node>>
}
