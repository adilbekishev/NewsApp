package dev.mohito.news.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Lazy
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mBinding = FragmentArticlesBinding.bind(view)
        val articles = ArrayList<Article>()
        val articlesAdapter = ArticleRecyclerAdapter(articles)

        mBinding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = articlesAdapter
        }

        articlesViewModel.articles.observe(viewLifecycleOwner, Observer {
            articles.addAll(it)
            articlesAdapter.notifyDataSetChanged()
        })

    }
}