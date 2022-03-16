package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo.db.MemoDao
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo.db.MemoDatabase
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap.db.NodeDao
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap.db.NodeDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideNodeDatabase(
        @ApplicationContext
        context: Context): NodeDatabase = synchronized(this) {
        Room.databaseBuilder(
            context.applicationContext, NodeDatabase::class.java, "node.db").build()
    }

    @Provides
    @Singleton
    fun provideNodeDao(db: NodeDatabase): NodeDao = db.nodeDao()

    @Provides
    @Singleton
    fun provideMemoDatabase(
        @ApplicationContext
        context: Context): MemoDatabase = synchronized(this) {
        Room.databaseBuilder(
            context.applicationContext, MemoDatabase::class.java, "memo.db").build()
    }

    @Provides
    @Singleton
    fun provideMemoDao(db: MemoDatabase): MemoDao = db.memoDao()
}
