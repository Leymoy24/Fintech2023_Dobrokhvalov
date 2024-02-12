package com.example.movie_poster.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movie_poster.data.database.FilmEntity
import com.example.movie_poster.data.repositories.Repository
import kotlinx.coroutines.launch

class FilmViewModel(application: Application, private val repository: Repository) :
    AndroidViewModel(application) {

    private val mutableFilmsList = MutableLiveData<MutableList<FilmEntity>>()
    private val favouriteFilmList = MutableLiveData<MutableList<FilmEntity>>()

    fun getListOfPopularFilms() = viewModelScope.launch {
        val filmItems = repository.getListOfPopularFilms()

        updateNewDataList(filmItems.toMutableList())
    }

    fun updateFilm(film: FilmEntity) = viewModelScope.launch {
        repository.updateFilm(film)
    }

    private fun updateNewDataList(newDataList: MutableList<FilmEntity>) {
        mutableFilmsList.value = newDataList
    }

    fun getNewDataList(): LiveData<MutableList<FilmEntity>> {
        return mutableFilmsList
    }

    fun getFavouriteDataList(): LiveData<MutableList<FilmEntity>> {
        val filmItems = repository.getFavouriteDataList()
        updateFavouriteDataList(filmItems)
        return favouriteFilmList
    }

    private fun updateFavouriteDataList(filmList: MutableList<FilmEntity>) = viewModelScope.launch {
        favouriteFilmList.value = filmList
    }

    class ViewModelFactory(
        private val application: Application,
        private val repository: Repository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FilmViewModel::class.java)) {
                return FilmViewModel(application, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}