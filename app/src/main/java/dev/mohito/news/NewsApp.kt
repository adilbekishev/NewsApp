package dev.mohito.news

import android.app.Application
import dev.mohito.news.details.NewsDetailDeps
import dev.mohito.news.details.NewsDetailDepsProvider
import dev.mohito.news.di.AppComponent
import dev.mohito.news.di.DaggerAppComponent

class NewsApp : Application(), NewsDetailDepsProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .apiKey(BuildConfig.NEWS_API_KEY)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override val deps: NewsDetailDeps
        get() = appComponent
}