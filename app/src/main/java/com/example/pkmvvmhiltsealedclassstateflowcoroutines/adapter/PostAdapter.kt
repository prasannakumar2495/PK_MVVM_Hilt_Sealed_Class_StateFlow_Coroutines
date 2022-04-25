package com.example.pkmvvmhiltsealedclassstateflowcoroutines.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.databinding.EachrowBinding
import com.example.pkmvvmhiltsealedclassstateflowcoroutines.modelclasses.PostItem

class PostAdapter(private var postList: List<PostItem>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(binding: EachrowBinding) : RecyclerView.ViewHolder(binding.root) {
        val tasks = binding.tasks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EachrowBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.tasks.text = postList[position].title
    }

    override fun getItemCount(): Int = postList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(postList: List<PostItem>) {
        this.postList = postList
        notifyDataSetChanged()
    }
}