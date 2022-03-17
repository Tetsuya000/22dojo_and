package jp.gr.java.conf.tmproject.dojoandroid2022.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo

@Entity(tableName = "memo_table")
data class MemoEntity(
    @PrimaryKey
    val nodeId: Int,
    val title: String,
    val memo: String) {

    fun toDomain(): Memo = Memo(nodeId = nodeId, title = title, memo = memo)
}
