package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.RoadmapLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMapModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoadmapRepositoryImpl @Inject constructor(
    private val roadmapLocalDataSource: RoadmapLocalDataSource) : RoadmapRepository {

    override fun parseRodeMap(): RoadMapModel = roadmapLocalDataSource.parseRodeMap()

    override suspend fun saveNode(node: Node) = roadmapLocalDataSource.saveNode(node)

    override suspend fun deleteNode(node: Node) = roadmapLocalDataSource.deleteNode(node)

    override fun loadMasterNode(): Flow<List<Node>> = roadmapLocalDataSource.loadMasterNode()
}
