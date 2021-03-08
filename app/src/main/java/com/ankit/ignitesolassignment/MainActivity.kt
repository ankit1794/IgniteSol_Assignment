package com.ankit.ignitesolassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        cvFiction.setOnClickListener {
            goToBookList("fiction")
        }
        cvDrama.setOnClickListener {
            goToBookList("drama")
        }
        cvHumor.setOnClickListener {
            goToBookList("humor")
        }
        cvPolitics.setOnClickListener {
            goToBookList("politics")
        }
        cvPhilosophy.setOnClickListener {
            goToBookList("philosophy")
        }
        cvHistory.setOnClickListener {
            goToBookList("history")
        }
        cvAdventure.setOnClickListener {
            goToBookList("adventure")
        }

    }

    fun goToBookList(topic: String) {
        val intent = Intent(this, BooksListActivity::class.java).apply {
            putExtra("topic", topic)
        }
        startActivity(intent)
    }
}
