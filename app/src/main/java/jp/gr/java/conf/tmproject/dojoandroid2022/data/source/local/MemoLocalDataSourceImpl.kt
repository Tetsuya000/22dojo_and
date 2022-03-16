package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.db.MemoDao
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import javax.inject.Inject

class MemoLocalDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoLocalDataSource {

    override suspend fun saveMemo(memo: Memo) = memoDao.insert(memo.toEntity())

    override suspend fun deleteMemo(id: Int) = memoDao.delete(id)

    override suspend fun loadAllMemo(): List<Memo> = memoDao.loadAllMemo().map { it.toDomain() }

    override suspend fun loadMemoById(id: Int): List<Memo> = memoDao.loadMemoById(id).map { it.toDomain() }
}
