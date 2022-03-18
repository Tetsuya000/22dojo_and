package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo.MemoLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoLocalDataSource: MemoLocalDataSource
) : MemoRepository {

    override suspend fun saveMemo(memo: Memo) = memoLocalDataSource.saveMemo(memo)

    override suspend fun deleteMemo(id: Int) = memoLocalDataSource.deleteMemo(id)

    override suspend fun loadAllMemo(): Flow<List<Memo>> = memoLocalDataSource.loadAllMemo()

    override suspend fun loadMemoById(id: Int): Flow<List<Memo>> = memoLocalDataSource.loadMemoById(id)
}
