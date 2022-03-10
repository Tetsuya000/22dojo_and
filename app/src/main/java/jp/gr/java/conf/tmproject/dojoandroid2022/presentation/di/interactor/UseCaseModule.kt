package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.interactor

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor.GetCharacterLevelUseCase
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor.GetCharacterLevelUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetCharacterLevelUseCase(impl: GetCharacterLevelUseCaseImpl): GetCharacterLevelUseCase
}
