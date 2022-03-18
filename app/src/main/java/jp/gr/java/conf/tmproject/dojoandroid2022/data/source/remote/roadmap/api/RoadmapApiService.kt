package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.roadmap.api

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Roadmap
import retrofit2.Response
import retrofit2.http.GET

interface RoadmapApiService {
    @GET("/CyberAgentHack/22dojo_and/main/assets/android-developer-roadmap2022.json?token=GHSAT0AAAAAABOKOI32V3WUMVDEJWV2ALEYYRZGTMQ")
    suspend fun fetchRoadmap(): Response<Roadmap>
}
