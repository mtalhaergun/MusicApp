package com.example.musicapp.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.RecyclerFavoriteLayoutBinding
import com.example.musicapp.databinding.RecyclerSongLayoutBinding
import com.example.musicapp.model.favorite.Favorites
import com.example.musicapp.model.track.Data
import com.example.musicapp.room.FavoritesDao
import javax.inject.Inject

class FavoritesRecyclerAdapter : RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesVH>() {

    var favoriteList = emptyList<Favorites>()

    class FavoritesVH(private val binding : RecyclerFavoriteLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite : Favorites){
            binding.favorite = favorite
            binding.duration.text = "%d:%02d".format((favorite.duration/60),(favorite.duration%60))
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerFavoriteLayoutBinding.inflate(layoutInflater,parent,false)
        return FavoritesVH(binding)
    }

    override fun onBindViewHolder(holder: FavoritesVH, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun setFavorites(list : List<Favorites>){
        favoriteList = list
        notifyDataSetChanged()
    }
}