package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo

interface MemoRepository {
    suspend fun saveMemo(memo: Memo)
    suspend fun deleteMemo(id: Int)
    suspend fun loadAllMemo(): List<Memo>
    suspend fun loadMemoById(id: Int): List<Memo>
}
