package com.example.pkmvvmhiltsealedclassstateflowcoroutines.util

import com.example.pkmvvmhiltsealedclassstateflowcoroutines.modelclasses.PostItem

sealed class ApiState {
    /**
     *we are using "object" where there is no need of giving any output.
     * we are using "class" where we should be giving an output.
     */
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: List<PostItem>) : ApiState()
    object Empty : ApiState()

}
