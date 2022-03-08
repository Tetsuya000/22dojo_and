package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.di.source

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.LocalDataSourceImpl
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.source.LocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource
}
