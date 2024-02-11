package com.example.movie_poster.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movie_poster.R
import com.example.movie_poster.databinding.FragmentFilmBinding


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
            binding.textViewFilmTitle.text = args.currentFilm!!.title
            binding.textViewFilmGenres.text = args.currentFilm!!.title
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