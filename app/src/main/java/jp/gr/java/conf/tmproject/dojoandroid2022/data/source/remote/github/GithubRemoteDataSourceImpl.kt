package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api.GitHubApiService
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class GithubRemoteDataSourceImpl @Inject constructor(
    private val gitHubApiService: GitHubApiService) : GithubRemoteDataSource {

    override suspend fun searchRepository(query: String): Response<SearchResponse> =
        gitHubApiService.searchRepository(query)
}
