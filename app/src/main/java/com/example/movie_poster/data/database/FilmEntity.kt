package com.example.movie_poster.data.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmEntity(
    val id: Int = 0,
    val poster: Int,
    val title: String,
    val description: String,
    val favourite: Boolean,
) : Parcelable