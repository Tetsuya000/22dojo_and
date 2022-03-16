package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Roadmap

interface RoadmapRemoteDataSource {
    suspend fun getRoadmap(): Roadmap
}
