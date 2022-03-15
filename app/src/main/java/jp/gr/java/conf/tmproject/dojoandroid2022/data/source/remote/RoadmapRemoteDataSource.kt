package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMap
import retrofit2.Response

interface RoadmapRemoteDataSource {
    suspend fun getRoadmap(): Response<RoadMap>
}
