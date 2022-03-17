package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun saveMemo(memo: Memo)
    suspend fun deleteMemo(id: Int)
    suspend fun loadAllMemo(): Flow<List<Memo>>
    suspend fun loadMemoById(id: Int): Flow<List<Memo>>
}
