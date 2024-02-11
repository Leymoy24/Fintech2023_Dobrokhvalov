package com.example.movie_poster.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_poster.R
import com.example.movie_poster.data.database.Film
import com.example.movie_poster.databinding.FragmentPopularBinding
import com.example.movie_poster.presentation.adapters.FilmAdapter


class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private val filmList = listOf(
        Film(0, R.drawable.avatar,"Изгой-один: Звёздные войны", "Фантастика (2016)", false),
        Film(1, R.drawable.nutcracker,"Щелкунчик", "Фэнтези (2018)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
        Film(2, R.drawable.black_adam,"Чёрный Адам", "Боевик (2022)", false),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        val topAppBar = activity?.findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        topAppBar?.title = getString(R.string.popular)

        val recyclerViewFilms: RecyclerView = view.findViewById(R.id.recyclerViewPopular)
        val adapter = FilmAdapter(filmList)

        recyclerViewFilms.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFilms.adapter = adapter

        val buttonFavourites = activity?.findViewById<Button>(R.id.button_favourites)
        buttonFavourites?.setOnClickListener {
            if (navController.currentDestination?.id != R.id.favouritesFragment) {
                navController.navigate(R.id.action_popularFragment_to_favouritesFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}