package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    @GET("/search/repositories")
    suspend fun searchRepository(
        @Query("q")
        query: String): Response<SearchResponse>
}
