package com.example.musicapp.ui.artistdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.RecyclerAlbumLayoutBinding
import com.example.musicapp.model.album.Data
import com.example.musicapp.ui.artistdetail.ArtistDetailFragmentDirections

class AlbumRecyclerAdapter : RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumVH>() {

    private var albums = emptyList<Data>()

    class AlbumVH(private val binding : RecyclerAlbumLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album : Data){
            binding.album = album
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerAlbumLayoutBinding.inflate(layoutInflater,parent,false)
        return AlbumVH(binding)
    }

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
        holder.bind(albums[position])

        holder.itemView.setOnClickListener {
            val navigation = ArtistDetailFragmentDirections.actionArtistDetailFragmentToTrackFragment(albums[position])
            Navigation.findNavController(it).navigate(navigation)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    fun setAlbums(list : List<Data>){
        albums = list
        notifyDataSetChanged()
    }
}