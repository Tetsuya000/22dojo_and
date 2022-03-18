package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo.db.MemoDao
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MemoLocalDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoLocalDataSource {

    override suspend fun saveMemo(memo: Memo) = memoDao.insert(memo.toEntity())

    override suspend fun deleteMemo(id: Int) = memoDao.delete(id)

    override suspend fun loadAllMemo(): Flow<List<Memo>> = memoDao.loadAllMemo().map { list ->
        list.map { it.toDomain() }
    }

    override suspend fun loadMemoById(id: Int): Flow<List<Memo>> = memoDao.loadMemoById(id).map { list ->
        list.map { it.toDomain() }
    }
}
