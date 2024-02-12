package com.example.movie_poster.presentation

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.movie_poster.R
import com.example.movie_poster.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()

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

        val window = window
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT

        // Удалить margin
        val marginLayoutParams =
            binding.fragmentContainerView.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.setMargins(0, 0, 0, 0)
    }

    private fun showToolbarAndBottomNavigation() {
        binding.appBarLayout.visibility = View.VISIBLE
        binding.bottomNavigation.visibility = View.VISIBLE

        val window = window
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.WHITE

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
//                filterList(newText)
                return true
            }
        })
        return true
    }

//    private fun filterList(query: String?) {
//        if (query != null) {
//            val filteredList = ArrayList<FilmEntity>()
//            for (i in MyApp.filmData) {
//                if (i.title.lowercase(Locale.ROOT).contains(query)) {
//                    filteredList.add(i)
//                }
//            }
//            if (filteredList.isEmpty()) {
//                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
//            } else {
//                filmAdapter.setFilteredList(filteredList)
//            }
//        }
//
//    }

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