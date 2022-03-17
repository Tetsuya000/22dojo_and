package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoLocalDataSource {
    suspend fun saveMemo(memo: Memo)
    suspend fun deleteMemo(id: Int)
    suspend fun loadAllMemo(): Flow<List<Memo>>
    suspend fun loadMemoById(id: Int): Flow<List<Memo>>
}
