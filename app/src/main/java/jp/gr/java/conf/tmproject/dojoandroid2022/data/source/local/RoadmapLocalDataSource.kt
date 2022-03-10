package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

interface RoadmapLocalDataSource {
    fun saveNode(nodeId: Int)
    fun deleteNode(nodeId: Int)
    fun getMasteryNodeId(): List<String>
    fun checkNodeMastery(targetNodeId: Int): Boolean
}
