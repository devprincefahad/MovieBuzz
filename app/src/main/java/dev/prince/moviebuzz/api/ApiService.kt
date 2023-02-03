package dev.prince.moviebuzz.api

import dev.prince.moviebuzz.data.Genre
import dev.prince.moviebuzz.data.GenreResponse
import dev.prince.moviebuzz.data.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3bebaee26c0d020b54e74f90800dca96"

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResult

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResult

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = API_KEY
    ): GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesGenre(
        @Query("with_genres") genreId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResult

}
