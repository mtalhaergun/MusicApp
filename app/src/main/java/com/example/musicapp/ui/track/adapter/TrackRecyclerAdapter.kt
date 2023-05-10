package com.example.musicapp.ui.track.adapter

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.RecyclerSongLayoutBinding
import com.example.musicapp.model.track.Data
import com.example.musicapp.ui.artistdetail.ArtistDetailFragment
import com.example.musicapp.ui.artistdetail.ArtistDetailFragmentDirections

class TrackRecyclerAdapter : RecyclerView.Adapter<TrackRecyclerAdapter.TrackVH>() {

    private var tracks = emptyList<Data>()
    var mediaPlayer : MediaPlayer? = null
    var tempMedia = MediaPlayer()


    class TrackVH(private val binding : RecyclerSongLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(track : Data){
            binding.track = track
            binding.duration.text = "%d:%02d".format((track.duration/60),(track.duration%60))

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackVH {
        val layoutInflayer = LayoutInflater.from(parent.context)
        val binding = RecyclerSongLayoutBinding.inflate(layoutInflayer,parent,false)
        return TrackVH(binding)
    }

    override fun onBindViewHolder(holder: TrackVH, position: Int) {
        holder.bind(tracks[position])

        holder.itemView.setOnClickListener {

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
}