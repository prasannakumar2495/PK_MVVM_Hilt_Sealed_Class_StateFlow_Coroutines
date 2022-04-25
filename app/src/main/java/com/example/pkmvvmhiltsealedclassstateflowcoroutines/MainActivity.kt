package com.example.pkmvvmhiltsealedclassstateflowcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.adapter.PostAdapter
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.databinding.ActivityMainBinding
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.util.ApiState
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    /**
     * instead of writing boiler plate code to create a variable for viewmodel, we can do it as below.
     * this as made possible for the below dependency:
     * "implementation 'androidx.activity:activity-ktx:1.4.0'"
     */
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        mainViewModel.getPost()
        lifecycleScope.launchWhenCreated {
            /**
             * we are using "lifecycleScope" because the data type of "_postStateFlow" is Flow,
             * which is part of Coroutines.
             */
            mainViewModel._postStateFlow.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.recyclerview.isVisible = true
                        postAdapter.setData(it.data)
                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        postAdapter = PostAdapter(ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}

/*
class NewClass @Inject constructor(private val data: String) {

}
*/