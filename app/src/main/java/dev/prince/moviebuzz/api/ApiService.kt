package dev.prince.moviebuzz.api

import dev.prince.moviebuzz.data.MovieResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3bebaee26c0d020b54e74f90800dca96"

interface ApiService {

    @GET("top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieResult>

   /* @GET("top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResult*/

}