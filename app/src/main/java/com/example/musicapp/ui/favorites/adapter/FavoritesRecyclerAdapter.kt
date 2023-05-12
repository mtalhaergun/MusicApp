package com.example.musicapp.ui.favorites.adapter

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.RecyclerFavoriteLayoutBinding
import com.example.musicapp.databinding.RecyclerSongLayoutBinding
import com.example.musicapp.model.favorite.Favorites
import com.example.musicapp.model.track.Data
import com.example.musicapp.room.FavoritesDao
import com.example.musicapp.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesRecyclerAdapter(private val viewModel: FavoritesViewModel) : RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesVH>() {

    var favoriteList = emptyList<Favorites>()
    var mediaPlayer : MediaPlayer? = null
    var tempMedia = MediaPlayer()
    var oldPosition = -1
    var fdao : FavoritesDao? = null

    class FavoritesVH(private val binding : RecyclerFavoriteLayoutBinding,private val viewModel: FavoritesViewModel) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite : Favorites,position: Int,fdao : FavoritesDao){
            binding.favorite = favorite
            binding.duration.text = "%d:%02d".format((favorite.duration/60),(favorite.duration%60))
            binding.executePendingBindings()

            binding.favoriteLikeImage.setOnClickListener {
                val job = CoroutineScope(Dispatchers.Main).launch {
                    fdao.deleteFavorites(favorite)
                    viewModel.favorites.value = fdao.getFavorites()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerFavoriteLayoutBinding.inflate(layoutInflater,parent,false)
        return FavoritesVH(binding,viewModel)
    }

    override fun onBindViewHolder(holder: FavoritesVH, position: Int) {
        holder.bind(favoriteList[position],position,fdao!!)

        holder.itemView.setOnClickListener {

            if(oldPosition != position){
                mediaPlayer = MediaPlayer()

                if(tempMedia.isPlaying){
                    tempMedia.stop()
                    tempMedia.reset()
                    tempMedia.release()
                }

                mediaPlayer?.apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                }

                mediaPlayer!!.setDataSource(favoriteList[position].preview)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()

                tempMedia = mediaPlayer!!
                oldPosition = position
            }else{
                if(tempMedia.isPlaying){
                    tempMedia.stop()
                }else{
                    tempMedia.start()
                }
                oldPosition = -1
            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun setFavorites(list : List<Favorites>){
        favoriteList = list
        notifyDataSetChanged()
    }

    fun stopMusic(){
        if(mediaPlayer != null){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
        }
    }

    fun getDao(favoritesDao : FavoritesDao){
        fdao = favoritesDao
    }
}