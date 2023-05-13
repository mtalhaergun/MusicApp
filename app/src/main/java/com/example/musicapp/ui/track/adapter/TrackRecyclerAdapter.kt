package com.example.musicapp.ui.track.adapter

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.databinding.RecyclerSongLayoutBinding
import com.example.musicapp.model.favorite.Favorites
import com.example.musicapp.model.track.Data
import com.example.musicapp.room.FavoritesDao
import com.example.musicapp.ui.artistdetail.ArtistDetailFragment
import com.example.musicapp.ui.artistdetail.ArtistDetailFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrackRecyclerAdapter : RecyclerView.Adapter<TrackRecyclerAdapter.TrackVH>() {

    private var tracks = emptyList<Data>()
    var mediaPlayer : MediaPlayer? = null
    var tempMedia = MediaPlayer()
    var oldPosition = -1
    var fdao : FavoritesDao? = null


    class TrackVH(private val binding : RecyclerSongLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(track : Data,position: Int,fdao : FavoritesDao){
            binding.track = track
            binding.duration.text = "%d:%02d".format((track.duration/60),(track.duration%60))
            val job = CoroutineScope(Dispatchers.Main).launch {
                if (fdao.searchName(track.title)) {
                    binding.likeImage.setImageResource(R.drawable.song_like_filled_icon)
                }else{
                    binding.likeImage.setImageResource(R.drawable.song_like_icon)
                }
            }
            binding.executePendingBindings()

            binding.likeImage.setOnClickListener {
                val job = CoroutineScope(Dispatchers.Main).launch {
                    val favorite = Favorites(0,track.duration,track.md5_image,track.preview,track.title)
                    if(fdao.searchName(track.title)){
                        fdao.deleteFavorites(favorite)
                        binding.likeImage.setImageResource(R.drawable.song_like_icon)
                    }else{
                        fdao.addFavorites(favorite)
                        binding.likeImage.setImageResource(R.drawable.song_like_filled_icon)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackVH {
        val layoutInflayer = LayoutInflater.from(parent.context)
        val binding = RecyclerSongLayoutBinding.inflate(layoutInflayer,parent,false)
        return TrackVH(binding)
    }

    override fun onBindViewHolder(holder: TrackVH, position: Int) {
        holder.bind(tracks[position],position,fdao!!)

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

                mediaPlayer!!.setDataSource(tracks[position].preview)
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
        return tracks.size
    }

    fun setTracks(list : List<Data>){
        tracks = list
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