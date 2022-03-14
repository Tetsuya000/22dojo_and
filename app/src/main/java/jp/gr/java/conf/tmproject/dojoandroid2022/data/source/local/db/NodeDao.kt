package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.db

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
    fun loadMasterNode(): Flow<List<NodeEntity>>

    @Query("SELECT * FROM node_table WHERE id == :nodeId")
    suspend fun loadSelectedNode(nodeId: Int): NodeEntity
}
