package com.example.catapi.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context): ImageLoader { // используем Glide

    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl) // откуда загружаем
            .centerCrop()
            .into(imageView) // куда загружаем
    }

}