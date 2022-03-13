package jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor

import kotlinx.coroutines.flow.Flow

interface GetCharacterLevelUseCase {
    fun getCharacterLevel(): Flow<Int>
}
