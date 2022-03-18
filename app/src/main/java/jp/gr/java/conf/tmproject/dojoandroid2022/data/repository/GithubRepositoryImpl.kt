package jp.gr.java.conf.tmproject.dojoandroid2022.data.repository

import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.GithubRemoteDataSource
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api.SearchResponse
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.GithubRepository
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource
) : GithubRepository {

    override suspend fun searchRepository(query: String): Response<SearchResponse> =
        githubRemoteDataSource.searchRepository(query)
}
