package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.LocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.RemoteDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMapModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.Repository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun parseRodeMap(): RoadMapModel = Json.decodeFromString(getJsonData())

    private fun getJsonData(): String {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open("android-developer-roadmap2022.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        return bufferedReader.readText()
    }

    override fun saveCharacterName(characterName: String) = localDataSource.saveCharacterName(characterName)

    override fun loadCharacterName(): String = localDataSource.loadCharacterName()
}
