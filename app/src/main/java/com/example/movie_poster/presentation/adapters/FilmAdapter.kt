package com.example.movie_poster.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_poster.utils.ItemDiffUtil
import com.example.movie_poster.R
import com.example.movie_poster.data.database.FilmEntity
import com.example.movie_poster.presentation.fragments.PopularFragmentDirections
import com.squareup.picasso.Picasso


class FilmAdapter : ListAdapter<FilmEntity, FilmAdapter.FilmViewHolder>(ItemDiffUtil()) {

    private var onItemLongClickListener: OnItemLongClickListener? = null

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item, parent, false)
        return FilmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val currentFilm = currentList[position]
        holder.onBind(currentFilm)

        if (currentFilm.isFavourite == true) {
            holder.imageButtonRating.visibility = View.VISIBLE
        } else {
            holder.imageButtonRating.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val action = PopularFragmentDirections.actionPopularFragmentToFilmFragment(currentFilm)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.setOnLongClickListener {
            val pos = holder.adapterPosition
            onItemLongClickListener?.onItemLongClick(pos)
            true
        }
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        onItemLongClickListener = listener
    }

    override fun getItemCount() = currentList.size

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private val textViewNameOfFilm: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewGenre: TextView = itemView.findViewById(R.id.textViewDescription)
        val imageButtonRating: ImageButton = itemView.findViewById(R.id.imageButtonRating)

        fun onBind(film: FilmEntity) {
            Picasso.get()
                .load(film.image)
                .resize(130, 205)
                .centerCrop()
                .into(imageViewPoster)
            textViewNameOfFilm.text = film.filmName
            textViewGenre.text = film.genre + " (" + film.year + ")"
        }
    }
}
