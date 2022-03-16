package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.NodeEntity

@Database(entities = [NodeEntity::class], version = 1, exportSchema = false)
abstract class NodeDatabase : RoomDatabase() {

    abstract fun nodeDao(): NodeDao
}
