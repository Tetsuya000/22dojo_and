package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.db.NodeDao
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoadmapLocalDataSourceImpl @Inject constructor(
    private val dao: NodeDao
) : RoadmapLocalDataSource {

    override suspend fun saveNode(node: Node) {
        dao.insert(node.toEntity())
    }

    override suspend fun deleteNode(node: Node) {
        dao.delete(node.toEntity())
    }

    override suspend fun loadAllNode(): Flow<List<Node>> {
        return dao.loadAllNode().map { nodes ->
            nodes.map { entity ->
                entity.toDomain()
            }
        }
    }
}
