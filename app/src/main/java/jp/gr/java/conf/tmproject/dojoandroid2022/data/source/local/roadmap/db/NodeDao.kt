package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.NodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(NodeEntity: NodeEntity)

    @Delete
    suspend fun delete(NodeEntity: NodeEntity)

    @Query("SELECT * FROM node_table")
    fun loadAllNode(): Flow<List<NodeEntity>>

    @Query("SELECT * FROM node_table WHERE nodeId == :id")
    fun loadNodeById(id: Int): NodeEntity
}
