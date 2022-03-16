package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.gr.java.conf.tmproject.dojoandroid2022.data.entity.MemoEntity

@Database(entities = [MemoEntity::class], version = 1, exportSchema = false)
abstract class MemoDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao
}
