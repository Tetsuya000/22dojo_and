package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.model

import kotlinx.serialization.Serializable

@Serializable
data class RoadMapModel(
    val paths: List<Path>
)

@Serializable
data class Path(
    val id: Int,
    val title: String,
    val sections: List<Section>
)

@Serializable
data class Section(
    val id: Int,
    val title: String,
    val isJetpack: Boolean,
    val nodes: List<Node>
)

@Serializable
data class Node(
    val id: Int,
    val title: String,
    val isJetpack: Boolean,
    val priority: Int,
    val childNodes: List<Node>? = null
)
