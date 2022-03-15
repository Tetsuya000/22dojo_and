package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.source

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.RoadmapRemoteDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.RoadmapRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindRoadmapRemoteDataSourceDataSource(impl: RoadmapRemoteDataSourceImpl): RoadmapRemoteDataSource
}
