package dev.mohito.news

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dev.mohito.news.details.ArticlesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
            fragmentManager.commit {
                add<ArticlesFragment>(FRAGMENTS_ARTICLES)
            }
        }
    }

    private companion object {
        private const val FRAGMENTS_ARTICLES = "articles"
    }
}