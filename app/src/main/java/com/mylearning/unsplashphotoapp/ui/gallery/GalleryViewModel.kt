package com.mylearning.unsplashphotoapp.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mylearning.unsplashphotoapp.data.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository) : ViewModel() {

    val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    /** A function that will set the current photo to the search photos **/
    fun searchPhotos(query : String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "cats"
    }

}