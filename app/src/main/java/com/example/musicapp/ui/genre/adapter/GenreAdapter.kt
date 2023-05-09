package com.example.musicapp.ui.genre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.FragmentGenreBinding
import com.example.musicapp.databinding.RecyclerGenreLayoutBinding
import com.example.musicapp.model.genre.Data
import com.example.musicapp.ui.genre.GenreFragmentDirections

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreVH>() {

    private var genres = emptyList<Data>()

    class GenreVH(private val binding: RecyclerGenreLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre : Data){
            binding.genre = genre
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerGenreLayoutBinding.inflate(layoutInflater,parent,false)
        return GenreVH(binding)
    }


    override fun onBindViewHolder(holder: GenreVH, position: Int) {
        holder.bind(genres[position])

        holder.itemView.setOnClickListener {
            val navigation = GenreFragmentDirections.actionGenreFragmentToArtistFragment(genres[position])
            Navigation.findNavController(it).navigate(navigation)
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    fun setGenres(list : List<Data>){
        genres = list
        notifyDataSetChanged()
    }
}