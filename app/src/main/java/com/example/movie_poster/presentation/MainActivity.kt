package com.example.movie_poster.presentation

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val popularFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? PopularFragment
        val recyclerView = popularFragment?.requireView()?.findViewById<RecyclerView>(R.id.recyclerViewPopular)

        filmAdapter = FilmAdapter(MyApp.filmData)
        recyclerView?.adapter = filmAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

//        searchView.isIconified = false
//        searchView.queryHint = resources.getString(R.string.search

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

}