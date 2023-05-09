package com.example.musicapp.ui.artist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.RecyclerArtistLayoutBinding
import com.example.musicapp.model.artist.Data
import com.example.musicapp.ui.artist.ArtistFragmentDirections

class ArtistRecyclerAdapter : RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistVH>() {

    private var artists = emptyList<Data>()

    class ArtistVH(private val binding : RecyclerArtistLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(artist : Data){
            binding.artist = artist
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerArtistLayoutBinding.inflate(layoutInflater,parent,false)
        return ArtistVH(binding)
    }

    override fun onBindViewHolder(holder: ArtistVH, position: Int) {
        holder.bind(artists[position])

        holder.itemView.setOnClickListener {
            val navigation = ArtistFragmentDirections.actionArtistFragmentToArtistDetailFragment(artists[position])
            Navigation.findNavController(it).navigate(navigation)
        }
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    fun setArtists(list : List<Data>){
        artists = list
        notifyDataSetChanged()
    }
}