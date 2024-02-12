package com.example.movie_poster.utils

import com.example.movie_poster.data.database.FilmEntity
import com.example.movie_poster.data.models.Film


class Converter {
    fun convertToFilmEntity(film: Film?, description: String): FilmEntity {
        var genreOfFilm = ""
        for(genre in film?.genres!!) {
            genreOfFilm = "$genreOfFilm${genre.genre}, "
        }

        var countriesOfFilm = ""
        for(country in film.countries!!) {
            countriesOfFilm = "$countriesOfFilm${country.country}, "
        }

        return FilmEntity(
            film.filmId!!,
            film.nameRu,
            film.year,
            genreOfFilm.removeRange(genreOfFilm.length - 2, genreOfFilm.length - 1),
            countriesOfFilm.removeRange(countriesOfFilm.length - 2, countriesOfFilm.length - 1),
            description,
            film.posterUrl,
            false
        )
    }
}