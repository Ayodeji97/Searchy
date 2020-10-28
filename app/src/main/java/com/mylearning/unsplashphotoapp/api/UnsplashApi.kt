package com.mylearning.unsplashphotoapp.api

import com.mylearning.unsplashphotoapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface UnsplashApi {

    // Companion object for access key
    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }

    /**
     * Headers are meta data above the request
     * Retrofit
     */
    @Headers("Accept-version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos (
        @Query("query") query : String,
        @Query("query") page : Int,
        @Query("per_page") perPage : Int
    ) : UnsplashResponse

}