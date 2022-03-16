package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.source

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.character.CharacterLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.character.CharacterLocalDataSourceImpl
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo.MemoLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.memo.MemoLocalDataSourceImpl
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap.RoadmapLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.roadmap.RoadmapLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindCharacterLocalDataSource(impl: CharacterLocalDataSourceImpl): CharacterLocalDataSource

    @Singleton
    @Binds
    abstract fun bindRoadmapLocalDataSource(impl: RoadmapLocalDataSourceImpl): RoadmapLocalDataSource

    @Singleton
    @Binds
    abstract fun bindMemoLocalDataSource(impl: MemoLocalDataSourceImpl): MemoLocalDataSource
}
