package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.repository.CharacterRepositoryImpl
import jp.gr.java.conf.tmproject.dojoandroid2022.data.repository.RoadmapRepositoryImpl
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.CharacterRepository
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    @Singleton
    abstract fun bindRoadmapRepository(impl: RoadmapRepositoryImpl): RoadmapRepository
}
