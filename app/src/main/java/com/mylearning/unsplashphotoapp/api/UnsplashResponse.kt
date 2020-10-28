package com.mylearning.unsplashphotoapp.api

import com.mylearning.unsplashphotoapp.data.UnsplashPhoto

data class UnsplashResponse  (
    val results : List<UnsplashPhoto>
)