package dev.mohito.news.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class NewsDetailsComponentViewModel(application: Application) : AndroidViewModel(application) {

    val newsDetailComponent: NewsDetailComponent by lazy {
        DaggerNewsDetailComponent.builder().deps(application.newsDetailDepsProvider.deps).build()
    }
}