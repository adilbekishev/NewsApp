package dev.mohito.news.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import dagger.Lazy
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mohito.news.details.databinding.FragmentArticlesBinding
import dev.mohito.news.news_api.Article
import javax.inject.Inject


class ArticlesFragment : Fragment() {

    @Inject
    internal lateinit var articlesViewModelFactory: Lazy<ArticlesViewModel.Factory>

    private val articlesViewModel: ArticlesViewModel by viewModels { articlesViewModelFactory.get() }
    private val detailsComponentViewModel: NewsDetailsComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        detailsComponentViewModel.newsDetailComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mBinding = FragmentArticlesBinding.bind(view)
        val articles = ArrayList<Article>()
        val articlesAdapter = ArticleRecyclerAdapter(articles)
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

//        Toast.makeText(requireContext(), mBinding.toString(), Toast.LENGTH_SHORT).show()
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager = linearLayoutManager
        mBinding.recyclerView.adapter = articlesAdapter

        articlesViewModel.articles.observe(viewLifecycleOwner, Observer {
            articles.clear()
            articles.addAll(it)
            articlesAdapter.notifyDataSetChanged()
        })

        articlesViewModel.getArticles()
    }
}

/*
* package dev.mohito.news.details

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
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager = linearLayoutManager
        mBinding.recyclerView.adapter = articlesAdapter

        articlesViewModel.articles.observe(viewLifecycleOwner, Observer {
            articles.clear()
            articles.addAll(it)
            articlesAdapter.notifyDataSetChanged()
        })

        articlesViewModel.getArticles()
    }
}*/