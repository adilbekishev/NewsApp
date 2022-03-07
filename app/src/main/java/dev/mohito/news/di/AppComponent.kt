package dev.mohito.news.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dev.mohito.news.details.NewsDetailDeps
import dev.mohito.news.news_api.NewsService
import javax.inject.Qualifier
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent : NewsDetailDeps {

    override val newsService: NewsService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiKey(@NewsApiQualifier apiKey: String): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideNewsService(@NewsApiQualifier apiKey: String): NewsService {
        return NewsService(apiKey)
    }
}

@Qualifier
annotation class NewsApiQualifier

@Scope
annotation class AppScope