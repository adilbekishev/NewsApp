package dev.mohito.news.details

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dev.mohito.news.news_api.NewsService
import javax.inject.Scope

@Scope
internal annotation class NewsDetailScope

@[NewsDetailScope Component(
    dependencies = [NewsDetailDeps::class],
    modules = [NewsDetailModule::class]
)]
interface NewsDetailComponent {
    fun inject(articlesFragment: ArticlesFragment)

    @Component.Builder
    interface Builder {

        fun deps(newsDetailDeps: NewsDetailDeps): Builder

        fun build(): NewsDetailComponent
    }
}

@Module
internal class NewsDetailModule {

}

interface NewsDetailDepsProvider {
    val deps: NewsDetailDeps
}

interface NewsDetailDeps {
    //comes from outside, it must already exist
    val newsService: NewsService
}

val Context.newsDetailDepsProvider: NewsDetailDepsProvider
    get() = when (this) {
        is NewsDetailDepsProvider -> this
        is Application -> error("Application must implements NewsDetailDepsProvider")
        else -> applicationContext.newsDetailDepsProvider
    }