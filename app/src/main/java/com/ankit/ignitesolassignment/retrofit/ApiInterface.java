package com.ankit.ignitesolassignment.retrofit;

import com.ankit.ignitesolassignment.model.Books;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/books")
    Call<Books> getBooksData(@Query("topic") String topic, @Query("page") int page);
}
