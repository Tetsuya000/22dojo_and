package jp.gr.java.conf.tmproject.dojoandroid2022.domain.model

import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.MemoEntity

data class Memo(
    val id: Int = 0,
    val memo: String = "") {
    fun toEntity(): MemoEntity = MemoEntity(id = id, memo = memo)
}
