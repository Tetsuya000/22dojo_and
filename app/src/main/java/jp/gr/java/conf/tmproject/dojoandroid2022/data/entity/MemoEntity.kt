package jp.gr.java.conf.tmproject.dojoandroid2022.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo

@Entity(tableName = "memo_table")
data class MemoEntity(
    @PrimaryKey
    val id: Int = 0,
    val memo: String = "") {

    fun toDomain(): Memo = Memo(id = id, memo = memo)
}
