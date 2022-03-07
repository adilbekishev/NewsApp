package dev.mohito.news.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dev.mohito.news.details.databinding.ItemArticleBinding
import dev.mohito.news.news_api.Article

class ArticleRecyclerAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Toast.makeText(parent.context, "created", Toast.LENGTH_SHORT).show()
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(articles[position])

    override fun getItemCount() = articles.size

    inner class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.apply {
                title.text = item.title
                content.text = item.content?:"Null comes here"
            }
        }
    }
}
