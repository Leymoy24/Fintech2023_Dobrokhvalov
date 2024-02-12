package com.example.movie_poster.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_poster.App
import com.example.movie_poster.R
import com.example.movie_poster.databinding.FragmentFavouritesBinding
import com.example.movie_poster.domain.FilmViewModel
import com.example.movie_poster.presentation.adapters.FilmAdapter


class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewModel: FilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireActivity().application as App
        val repository = application.repository
        viewModel = ViewModelProvider(
            this,
            FilmViewModel.ViewModelFactory(application, repository)
        )[FilmViewModel::class.java]

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarFavourite)
        progressBar.visibility = View.VISIBLE

        navController = findNavController()

        val topAppBar = activity?.findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        topAppBar?.title = getString(R.string.favourites)

        val recyclerViewFilms: RecyclerView = view.findViewById(R.id.recyclerViewFavourite)
        val adapter = FilmAdapter()

        recyclerViewFilms.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFilms.adapter = adapter
        recyclerViewFilms.setHasFixedSize(true)

        viewModel.getFavouriteDataList().observe(viewLifecycleOwner) { dataList ->
            adapter.submitList(dataList)
            progressBar.visibility = View.INVISIBLE
        }

        adapter.setOnItemLongClickListener(object : FilmAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                val film = adapter.currentList[position]
                film.isFavourite = film.isFavourite != true
                viewModel.updateFilm(film)

                viewModel.getFavouriteDataList().observe(viewLifecycleOwner) { dataList ->
                    adapter.submitList(dataList)
                }
            }
        })

        val buttonPopular = activity?.findViewById<Button>(R.id.button_popular)
        buttonPopular?.setOnClickListener {
            if (navController.currentDestination?.id != R.id.popularFragment) {
                navController.navigate(R.id.action_favouritesFragment_to_popularFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}