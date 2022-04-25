package com.example.pkmvvmhiltsealedclassstateflowcoroutines.repository

import com.example.pkmvvmhiltsealedclassstateflowcoroutines.modelclasses.PostItem
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.network.ApiServiceImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiServiceImplementation: ApiServiceImplementation) {
    /**
     * we are going to use Flows to fetch the data.
     * Flows --> it fetches all the data at a shot asynchronously
     *           and send data one after the another.
     */

    fun getPost(): Flow<List<PostItem>> = flow {
        emit(apiServiceImplementation.getPost())
    }.flowOn(Dispatchers.IO)
}