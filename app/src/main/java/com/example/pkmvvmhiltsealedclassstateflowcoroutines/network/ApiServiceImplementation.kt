package com.example.pkmvvmhiltsealedclassstateflowcoroutines.network

import com.example.pkmvvmhiltsealedclassstateflowcoroutines.modelclasses.PostItem
import javax.inject.Inject

class ApiServiceImplementation @Inject constructor(private val apiService: ApiService) {
    suspend fun getPost(): List<PostItem> = apiService.getPost()
}