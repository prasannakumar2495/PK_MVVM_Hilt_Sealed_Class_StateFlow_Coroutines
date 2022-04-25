package com.example.pkmvvmhiltsealedclassstateflowcoroutines.network

import com.example.pkmvvmhiltsealedclassstateflowcoroutines.modelclasses.PostItem
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPost(): List<PostItem>
}