package com.example.moviesapp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Json {
    @GET("movie")
    Call<Pages> getMovies(


            @Query("api_key") String api_key,
              @Query("sort_by") String sort_by
    );

}
