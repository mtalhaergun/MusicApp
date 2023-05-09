package com.example.musicapp.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.musicapp.utils.Extensions.loadImage

class ImageBinding {
    companion object{

        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView, characterImage : String){
            val imageUrl = characterImage
            imageView.loadImage(imageUrl)
        }

    }
}