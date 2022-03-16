package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.source

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.GithubRemoteDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.GithubRemoteDataSourceImpl
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.roadmap.RoadmapRemoteDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.roadmap.RoadmapRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindRoadmapRemoteDataSourceDataSource(impl: RoadmapRemoteDataSourceImpl): RoadmapRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindGithubRemoteDataSourceDataSource(impl: GithubRemoteDataSourceImpl): GithubRemoteDataSource
}
