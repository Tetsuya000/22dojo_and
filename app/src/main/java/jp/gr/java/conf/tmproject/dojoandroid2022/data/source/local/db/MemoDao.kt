package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.MemoEntity

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memoEntity: MemoEntity)

    @Query("DELETE FROM memo_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM memo_table")
    suspend fun loadAllMemo(): List<MemoEntity>

    @Query("SELECT * FROM memo_table WHERE id == :id")
    suspend fun loadMemoById(id: Int): List<MemoEntity>
}
