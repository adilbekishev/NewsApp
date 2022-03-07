package dev.mohito.news.details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Lazy
import dev.mohito.news.details.databinding.FragmentArticlesBinding
import dev.mohito.news.news_api.Article
import javax.inject.Inject

class ArticlesFragment : Fragment(R.layout.fragment_articles) {

    @Inject
    internal lateinit var articlesViewModelFactory: Lazy<ArticlesViewModel.Factory>

    private val articlesViewModel: ArticlesViewModel by viewModels { articlesViewModelFactory.get() }
    private val detailsComponentViewModel: NewsDetailsComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        detailsComponentViewModel.newsDetailComponent.inject(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mBinding = FragmentArticlesBinding.bind(view)
        val articles = ArrayList<Article>()
        val articlesAdapter = ArticleRecyclerAdapter(articles)
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

//        Toast.makeText(requireContext(), mBinding.toString(), Toast.LENGTH_SHORT).show()

        mBinding.recyclerView.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = articlesAdapter
            setHasFixedSize(true)
        }

        articlesViewModel.articles.observe(viewLifecycleOwner, Observer {
            articles.addAll(it)
            articlesAdapter.notifyDataSetChanged()
        })

        articlesViewModel.getArticles()
    }
}