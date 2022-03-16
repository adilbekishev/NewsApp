package dev.mohito.news.news_api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/top-headlines")
    suspend fun topHeadlines(@Query("country") country: String): Articles
}

fun NewsService(apiKey: String): NewsService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { interceptorChain ->
            val authorizedRequest = interceptorChain.request().newBuilder()
                .addHeader(HEADER_API_KEY, apiKey)
                .build()

            interceptorChain.proceed(authorizedRequest)
        }
        .build()

    val json = Json {
        ignoreUnknownKeys = true
    }

    val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(MediaType.parse("application/json")!!))
        .build()

    return retrofit.create(NewsService::class.java)
}

private const val HEADER_API_KEY = "X-Api-Key"