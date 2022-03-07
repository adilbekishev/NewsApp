package dev.mohito.news.news_api

import kotlinx.serialization.Serializable

@Serializable
data class Articles(
    val articles: List<Article>
)