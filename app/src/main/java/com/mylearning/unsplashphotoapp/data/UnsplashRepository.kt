package com.mylearning.unsplashphotoapp.data

import UnsplashPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mylearning.unsplashphotoapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository  @Inject constructor(private val unsplashApi: UnsplashApi)  {

    // Function to get the user search
    fun getSearchResults(query : String) =
        // The pager will use the paging source to create the paging data
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query)}
        ).liveData

}