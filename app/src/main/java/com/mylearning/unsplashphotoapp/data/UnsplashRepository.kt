package com.mylearning.unsplashphotoapp.data

import com.mylearning.unsplashphotoapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository  @Inject constructor(private val unsplashApi: UnsplashApi)  {

}