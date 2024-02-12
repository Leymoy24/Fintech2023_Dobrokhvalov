package com.example.movie_poster.data.repositories

import com.example.movie_poster.utils.Converter
import com.example.movie_poster.api.ApiService
import com.example.movie_poster.data.database.FilmEntity
import com.example.movie_poster.data.database.FilmsDao


class Repository(private val filmsDao: FilmsDao) {

    private val converter: Converter = Converter()

    private val apiService by lazy {
        ApiService.create()
    }

    suspend fun getListOfPopularFilms(): List<FilmEntity> {
        val listOfFilms = apiService.getPopularFilms()
        var description = ""

        for(film in listOfFilms) {
            description = apiService.getDescriptionOfFilm(film.filmId!!).description.toString()
            val filmEntity = converter.convertToFilmEntity(film, description)
            filmsDao.insertFilm(filmEntity)
        }

        return filmsDao.getAllFilms()
    }

    suspend fun updateFilm(film: FilmEntity) {
        filmsDao.updateFilm(film)
    }
}