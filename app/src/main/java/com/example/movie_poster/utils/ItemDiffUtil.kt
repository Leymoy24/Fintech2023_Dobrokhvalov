package com.example.movie_poster.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.movie_poster.data.database.FilmEntity

class ItemDiffUtil : DiffUtil.ItemCallback<FilmEntity>() {
    override fun areItemsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
        return oldItem == newItem
    }
}
