package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.di.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.api.RoadmapApiService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val client: OkHttpClient =
        OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val baseUrl = "https://raw.githubusercontent.com/"
        val format = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(format.asConverterFactory(contentType))
            .client(client).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RoadmapApiService =
        retrofit.create(RoadmapApiService::class.java)
}
