package com.example.pkmvvmhiltsealedclassstateflowcoroutines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.repository.MainRepository
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    /**
     * initially we have set the state of the network as "Empty" as there is no work that is happening.
     */
    private val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val _postStateFlow: StateFlow<ApiState> = postStateFlow

    fun getPost() = viewModelScope.launch {
        /**
         * as we call this method, the network starts loading the data.
         * so we will be setting the state as "Loading"
         *
         * next, we will be fetching the data from the mainRepository, but there is always a chance
         * of failure, so we have added the catch block.
         *
         * if the work has been success, then we will collect it.
         */
        postStateFlow.value = ApiState.Loading
        mainRepository.getPost()
            .catch { error ->
                postStateFlow.value = ApiState.Failure(error)
            }
            .collect { data ->
                postStateFlow.value = ApiState.Success(data)
            }
    }
}