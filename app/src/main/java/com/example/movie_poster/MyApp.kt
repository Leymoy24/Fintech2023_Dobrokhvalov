package com.example.movie_poster

import android.app.Application
import com.example.movie_poster.data.database.FilmEntity

class MyApp : Application() {

    companion object {
        lateinit var filmData: MutableList<FilmEntity>
    }

    override fun onCreate() {
        super.onCreate()

        filmData = mutableListOf(
            FilmEntity(0, R.drawable.avatar,"Изгой-один: Звёздные войны", "Фантастика (2016)", false),
            FilmEntity(1, R.drawable.nutcracker,"Щелкунчик", "Фэнтези (2018)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
            FilmEntity(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        )
    }
}