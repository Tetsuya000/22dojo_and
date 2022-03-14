package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.db.NodeDao
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.db.NodeDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideNodeDatabase(
        @ApplicationContext
        context: Context
    ): NodeDatabase = synchronized(this) {
        Room.databaseBuilder(
            context.applicationContext, NodeDatabase::class.java, "node.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNodeDao(db: NodeDatabase): NodeDao = db.nodeDao()
}
