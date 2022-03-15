package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.api.RoadmapApiService
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.RoadMap
import retrofit2.Response
import javax.inject.Inject

class RoadmapRemoteDataSourceImpl @Inject constructor(
    private val roadmapApiService: RoadmapApiService
) : RoadmapRemoteDataSource {

    override suspend fun getRoadmap(): Response<RoadMap> = roadmapApiService.getRoadmap()
}
