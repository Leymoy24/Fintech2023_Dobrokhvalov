package com.example.movie_poster.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movie_poster.R
import com.example.movie_poster.databinding.FragmentFilmBinding


class FilmFragment : Fragment() {

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<FilmFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val appBarLayout = activity?.findViewById<com.google.android.material.appbar.AppBarLayout>(R.id.appBarLayout)
        appBarLayout?.visibility = View.GONE

        val bottomNavigationLayout = activity?.findViewById<LinearLayout>(R.id.bottom_navigation)
        bottomNavigationLayout?.visibility = View.GONE

        val layoutParams = requireView().layoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        requireView().layoutParams = layoutParams

        if (args.currentFilm != null) {
            binding.textViewFilmTitle.text = args.currentFilm!!.title
            binding.textViewFilmGenres.text = args.currentFilm!!.title
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}