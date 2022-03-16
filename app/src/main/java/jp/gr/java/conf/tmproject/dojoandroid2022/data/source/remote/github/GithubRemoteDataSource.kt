package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api.SearchResponse
import retrofit2.Response

interface GithubRemoteDataSource {
    suspend fun searchRepository(query: String): Response<SearchResponse>
}
