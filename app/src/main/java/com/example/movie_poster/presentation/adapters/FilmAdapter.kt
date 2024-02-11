package com.example.movie_poster.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_poster.R
import com.example.movie_poster.data.database.FilmEntity
import com.example.movie_poster.presentation.PopularFragmentDirections


class FilmAdapter(private var filmEntityList: List<FilmEntity>) :
    RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item, parent, false)
        return FilmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val currentFilm = filmEntityList[position]
        holder.bind(currentFilm)

        holder.itemView.setOnClickListener {
            val action = PopularFragmentDirections.actionPopularFragmentToFilmFragment(currentFilm)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = filmEntityList.size

    fun setFilteredList(mList: List<FilmEntity>) {
        this.filmEntityList = mList
        notifyDataSetChanged()
    }

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private val textViewNameOfFilm: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewGenre: TextView = itemView.findViewById(R.id.textViewDescription)

        fun bind(filmEntity: FilmEntity) {
            imageViewPoster.setImageResource(filmEntity.poster)
            textViewNameOfFilm.text = filmEntity.title
            textViewGenre.text = filmEntity.description
        }
    }
}
