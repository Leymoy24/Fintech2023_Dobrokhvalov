package com.example.movie_poster.presentation

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_poster.MyApp
import com.example.movie_poster.R
import com.example.movie_poster.data.database.FilmEntity
import com.example.movie_poster.databinding.ActivityMainBinding
import com.example.movie_poster.presentation.adapters.FilmAdapter
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmAdapter: FilmAdapter
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val popularFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? PopularFragment
        val recyclerView =
            popularFragment?.requireView()?.findViewById<RecyclerView>(R.id.recyclerViewPopular)

        filmAdapter = FilmAdapter(MyApp.filmData)
        recyclerView?.adapter = filmAdapter
    }

    override fun onResume() {
        super.onResume()

        navController = findNavController(R.id.fragmentContainerView)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.filmFragment -> {
                    hideToolbarAndBottomNavigation()
                }

                else -> {
                    showToolbarAndBottomNavigation()
                }
            }
        }
    }

    private fun hideToolbarAndBottomNavigation() {
        binding.appBarLayout.visibility = View.GONE
        binding.bottomNavigation.visibility = View.GONE

        // Растянуть conteinerView
        val layoutParams = binding.fragmentContainerView.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        binding.fragmentContainerView.layoutParams = layoutParams

        // Удалить margin
        val marginLayoutParams =
            binding.fragmentContainerView.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.setMargins(0, 0, 0, 0)
    }

    private fun showToolbarAndBottomNavigation() {
        binding.appBarLayout.visibility = View.VISIBLE
        binding.bottomNavigation.visibility = View.VISIBLE

        // Восстановить предыдущие параметры conteinerView
        val layoutParams = binding.fragmentContainerView.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        binding.fragmentContainerView.layoutParams = layoutParams

        // Восстановить предыдущие margin
        val marginLayoutParams =
            binding.fragmentContainerView.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.setMargins(
            0,
            160,
            0,
            resources.getDimension(R.dimen.bottom_navigation_height).toInt()
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.isIconified = false
        searchView.queryHint = resources.getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
        return true
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<FilmEntity>()
            for (i in MyApp.filmData) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                filmAdapter.setFilteredList(filteredList)
            }
        }

    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        if (navController.currentDestination?.id == R.id.filmFragment) {
            navController.navigateUp()
        } else {
            super.onBackPressed()
        }
    }
}