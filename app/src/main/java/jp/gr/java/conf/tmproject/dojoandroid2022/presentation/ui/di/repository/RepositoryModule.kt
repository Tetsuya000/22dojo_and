package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.repository.RepositoryImpl
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}
