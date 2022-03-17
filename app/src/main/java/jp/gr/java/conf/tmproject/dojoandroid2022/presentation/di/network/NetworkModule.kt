package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api.GitHubApiService
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.roadmap.api.RoadmapApiService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    @Named("roadmap")
    fun provideRoadmapRetrofit(client: OkHttpClient): Retrofit {
        val baseUrl = "https://raw.githubusercontent.com/"
        val format = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(format.asConverterFactory(contentType))
            .client(client).build()
    }

    @Provides
    @Singleton
    fun provideRoadmapApiService(
        @Named("roadmap")
        retrofit: Retrofit): RoadmapApiService = retrofit.create(RoadmapApiService::class.java)

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    @Named("github")
    fun provideGithubRetrofit(client: OkHttpClient): Retrofit {
        val baseUrl = "https://api.github.com/"
        val format = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(format.asConverterFactory(contentType))
            .client(client).build()
    }

    @Provides
    @Singleton
    fun provideGithubApiService(
        @Named("github")
        retrofit: Retrofit): GitHubApiService = retrofit.create(GitHubApiService::class.java)
}
