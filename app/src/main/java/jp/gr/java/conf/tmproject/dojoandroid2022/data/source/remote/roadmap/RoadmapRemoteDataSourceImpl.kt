package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.roadmap

import android.accounts.NetworkErrorException
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.roadmap.api.RoadmapApiService
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Roadmap
import javax.inject.Inject

class RoadmapRemoteDataSourceImpl @Inject constructor(
    private val roadmapApiService: RoadmapApiService
) : RoadmapRemoteDataSource {

    override suspend fun fetchRoadmap(): Roadmap {
        val response = roadmapApiService.fetchRoadmap()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()!!.toString()
            throw NetworkErrorException(error)
        }
    }
}
