package jp.gr.java.conf.tmproject.dojoandroid2022.data.source.remote.github.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(val items: List<SearchItem>)

@Serializable
data class SearchItem(
    val id: Int,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("stargazers_count")
    val starCount: Int)
