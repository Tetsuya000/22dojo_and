package jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api.SearchResponse
import retrofit2.Response

interface GithubRepository {
    suspend fun searchRepository(query: String): Response<SearchResponse>
}
