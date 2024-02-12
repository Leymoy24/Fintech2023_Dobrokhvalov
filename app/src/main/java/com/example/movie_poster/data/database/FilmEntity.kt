package com.example.movie_poster.data.database


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "films_table")
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val filmName: String?,

    @ColumnInfo(name = "year")
    val year: String?,

    @ColumnInfo(name = "genre")
    val genre: String?,

    @ColumnInfo(name = "country")
    val country: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean?,

    ): Parcelable {
    constructor() : this(
        0,
        null,
        null,
        null,
        null,
        null,
        null,
        false
    )
}