package com.example.movie_poster.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseFilms<T>(
    val pagesCount: Int,
    val films: List<T>,
)