package dev.mohito.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.mohito.news.details.ArticlesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ArticlesFragment())
            .commit()
    }
}