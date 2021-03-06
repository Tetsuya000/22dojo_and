package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memoEntity: MemoEntity)

    @Query("DELETE FROM memo_table WHERE nodeId = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM memo_table")
    fun loadAllMemo(): Flow<List<MemoEntity>>

    @Query("SELECT * FROM memo_table WHERE nodeId == :id")
    fun loadMemoById(id: Int): Flow<List<MemoEntity>>
}
