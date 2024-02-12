package com.example.movie_poster

import android.app.Application
import com.example.movie_poster.data.database.RoomData
import com.example.movie_poster.data.repositories.Repository

class App: Application() {

    val database by lazy { RoomData.getDatabase(this) }

    val repository by lazy { Repository(database.filmsDao()) }
}