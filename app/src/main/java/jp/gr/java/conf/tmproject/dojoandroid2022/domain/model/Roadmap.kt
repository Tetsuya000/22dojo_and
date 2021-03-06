package jp.gr.java.conf.tmproject.dojoandroid2022.domain.model

import android.os.Parcelable
import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.NodeEntity
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Roadmap(
    val paths: List<Path>
) : Parcelable

@Serializable
@Parcelize
data class Path(
    val id: Int,
    val title: String,
    val sections: List<Section>
) : Parcelable

@Serializable
@Parcelize
data class Section(
    val id: Int,
    val title: String,
    val isJetpack: Boolean,
    val nodes: List<Node>
) : Parcelable

@Serializable
@Parcelize
data class Node(
    val id: Int,
    val title: String,
    val isJetpack: Boolean,
    val priority: Int,
    val childNodes: List<Node> = emptyList()
) : Parcelable {

    fun toEntity(): NodeEntity = NodeEntity(nodeId = id, title = title, isJetpack = isJetpack, priority = priority)
}
