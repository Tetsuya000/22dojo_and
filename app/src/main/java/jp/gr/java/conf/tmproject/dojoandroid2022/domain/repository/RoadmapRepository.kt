package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMapModel

interface RoadmapRepository {
    fun parseRodeMap(): RoadMapModel
    fun saveNode(nodeId: Int)
    fun deleteNode(nodeId: Int)
    fun getMasteryNodeId(): List<String>
    fun checkNodeMastery(targetNodeId: Int): Boolean
}
