package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoadMapModel(
    @SerialName("paths")
    val paths: List<Path>
)

@Serializable
data class Path(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("sections")
    val sections: List<Section>
)

@Serializable
data class Section(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("isJetpack")
    val isJetpack: Boolean,
    @SerialName("nodes")
    val nodes: List<Node>
)

@Serializable
data class Node(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("isJetpack")
    val isJetpack: Boolean,
    @SerialName("priority")
    val priority: Int,
    @SerialName("childNodes")
    val childNodes: List<Node>? = null
)
