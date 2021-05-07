package com.example.catapi.image

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView) // загружаем url и саму фотографию

}