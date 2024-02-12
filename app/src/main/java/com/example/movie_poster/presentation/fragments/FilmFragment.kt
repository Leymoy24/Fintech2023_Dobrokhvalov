package com.example.movie_poster.presentation.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movie_poster.R
import com.example.movie_poster.databinding.FragmentFilmBinding
import com.squareup.picasso.Picasso


class FilmFragment : Fragment() {

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<FilmFragmentArgs>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        if (args.currentFilm != null) {
            Picasso.get().load(args.currentFilm!!.image.toString()).into(binding.imageViewImage)
            binding.textViewFilmTitle.text = args.currentFilm!!.filmName
            binding.textViewFilmDescription.text = args.currentFilm!!.description

            val spannableGenres = SpannableString("Жанры: ${args.currentFilm!!.genre}")
            val spannableCountries = SpannableString("Страны: ${args.currentFilm!!.country}")
            val spannableYear = SpannableString("Год: ${args.currentFilm!!.year}")

            spannableGenres.setSpan(StyleSpan(Typeface.BOLD), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableCountries.setSpan(StyleSpan(Typeface.BOLD), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableYear.setSpan(StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            binding.textViewFilmGenres.text = spannableGenres
            binding.textViewFilmCountries.text = spannableCountries
            binding.textViewFilmYear.text = spannableYear
        }

        binding.imageButtonBack.setOnClickListener {
            navController.navigate(R.id.action_filmFragment_to_popularFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}