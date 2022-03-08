package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.di.source

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.RemoteDataSourceImpl
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.source.RemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
}
