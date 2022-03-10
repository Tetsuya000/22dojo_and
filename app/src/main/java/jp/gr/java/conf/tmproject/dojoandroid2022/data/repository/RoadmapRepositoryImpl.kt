package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.local.RoadmapLocalDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMapModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class RoadmapRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val roadmapLocalDataSource: RoadmapLocalDataSource
) : RoadmapRepository {

    override fun parseRodeMap(): RoadMapModel {
        val assetManager = context.resources.assets
        val inputStream = assetManager.open("android-developer-roadmap2022.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonData = bufferedReader.readText()

        return Json.decodeFromString(jsonData)
    }

    override fun saveNode(nodeId: Int) = roadmapLocalDataSource.saveNode(nodeId)

    override fun deleteNode(nodeId: Int) = roadmapLocalDataSource.deleteNode(nodeId)

    override fun checkNodeMastery(targetNodeId: Int): Boolean = roadmapLocalDataSource.checkNodeMastery(targetNodeId)
}
