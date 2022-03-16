package jp.gr.java.conf.tmproject.dojoandroid2022.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node

@Entity(tableName = "node_table")
data class NodeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    @ColumnInfo(name = "is_jetpack")
    val isJetpack: Boolean,
    val priority: Int) {

    fun toDomain(): Node = Node(id = id, title = title, isJetpack = isJetpack, priority = priority, childNodes = emptyList())
}
