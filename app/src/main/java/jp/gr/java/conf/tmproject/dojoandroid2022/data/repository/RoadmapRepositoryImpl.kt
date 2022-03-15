package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.NodeEntity
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.RoadmapLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.RoadmapRemoteDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMap
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RoadmapRepositoryImpl @Inject constructor(
    private val roadmapLocalDataSource: RoadmapLocalDataSource,
    private val roadmapRemoteDataSource: RoadmapRemoteDataSource
) : RoadmapRepository {

    override suspend fun getRodeMap(): Response<RoadMap> = roadmapRemoteDataSource.getRoadmap()

    override fun parseRodeMap(): RoadMap = roadmapLocalDataSource.parseRodeMap()

    override suspend fun saveNode(node: Node) = roadmapLocalDataSource.saveNode(node)

    override suspend fun deleteNode(node: Node) = roadmapLocalDataSource.deleteNode(node)

    override fun loadMasterNode(): Flow<List<Node>> = roadmapLocalDataSource.loadMasterNode()

    override suspend fun loadSelectedNode(nodeId: Int): NodeEntity =
        roadmapLocalDataSource.loadSelectedNode(nodeId)
}
