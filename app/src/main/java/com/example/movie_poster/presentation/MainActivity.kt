package com.example.movie_poster.presentation

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie_poster.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val toolbar = findViewById<MaterialToolbar>(R.id.)
//        toolbar.setNavigationIcon(R.drawable.ic_your_icon)
//        toolbar.setNavigationOnClickListener {
//            // Ваш код обработки щелчка на иконке
//        }

    }
}