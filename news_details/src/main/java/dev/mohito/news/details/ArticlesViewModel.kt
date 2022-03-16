package dev.mohito.news.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.mohito.news.news_api.Article
import dev.mohito.news.news_api.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class ArticlesViewModel(private val newsService: NewsService) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles = _articles

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = newsService.topHeadlines("us")
            _articles.postValue(data.articles)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val newsService: Provider<NewsService>) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ArticlesViewModel::class.java)
            return ArticlesViewModel(newsService.get()) as T
        }
    }
}