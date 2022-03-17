package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.NodeEntity
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap.RoadmapLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.roadmap.RoadmapRemoteDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Roadmap
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoadmapRepositoryImpl @Inject constructor(
    private val roadmapLocalDataSource: RoadmapLocalDataSource,
    private val roadmapRemoteDataSource: RoadmapRemoteDataSource
) : RoadmapRepository {

    override suspend fun getRoadmap(): Roadmap = roadmapRemoteDataSource.getRoadmap()

    override suspend fun saveNode(node: Node) = roadmapLocalDataSource.saveNode(node)

    override suspend fun deleteNode(node: Node) = roadmapLocalDataSource.deleteNode(node)

    override fun loadAllNode(): Flow<List<Node>> = roadmapLocalDataSource.loadAllNode()

    override suspend fun loadNodeById(id: Int): NodeEntity = roadmapLocalDataSource.loadNodeById(id)
}
