package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.LocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.RemoteDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun saveCharacterName(characterName: String) = localDataSource.saveCharacterName(characterName)

    override fun loadCharacterName(): String = localDataSource.loadCharacterName()
}
