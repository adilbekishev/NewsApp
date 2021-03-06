package dev.mohito.news.news_api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Article(
    val title: String?,
    val content: String?
)