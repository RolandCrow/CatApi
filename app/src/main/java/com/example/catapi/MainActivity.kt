package com.example.catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.catapi.api.TheCatApiService
import com.example.catapi.data.CatBreedData
import com.example.catapi.data.ImageResultData
import com.example.catapi.image.GlideImageLoader
import com.example.catapi.image.ImageLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private val agentBreedView: TextView by lazy {
        findViewById(R.id.main_agent_breed_value)
    }

    private val profileImageView:ImageView by lazy{
        findViewById(R.id.main_profile_image) // для подстановки фото в активити
    }

    private val imageLoader: ImageLoader by lazy {
        GlideImageLoader(this) // присоединяем Glide
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create()) // применяем moshi
            .build()
    }

    private val theCatApiService by lazy { // инициация как можно позже создается только когда нужно
        retrofit.create(TheCatApiService::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCatImageResponse() // вызыв
    }


    private fun getCatImageResponse() { // функция получения описания фото
        val call = theCatApiService.searchImages(1,"full")
        call.enqueue(object : Callback<List<ImageResultData>> {
            override fun onFailure(call: Call<List<ImageResultData>>, t: Throwable) {
                Log.e("MainActivity", "Failed to get search results", t)
            }

            override fun onResponse(call: Call<List<ImageResultData>>, response:
            Response<List<ImageResultData>>) {
                if(response.isSuccessful) {
                   val imageResult = response.body()
                    val firstImageUrl = imageResult?.firstOrNull()?.imageUrl ?: ""
                    if(firstImageUrl.isNotBlank()) {
                        imageLoader.loadImage(firstImageUrl,profileImageView )
                    } else {
                        Log.d("MainActivity", "Missing image URL")
                    }
                    agentBreedView.text = imageResult?.firstOrNull()?.breeds?.firstOrNull()?.name
                        ?: "Unknown" // добавляем имя с сайта
                } else {
                    Log.e("MainActivity", "Failed to get search results\\n\n" +
                            "${response.errorBody()?.string() ?: ""}\"")
                }
            }
            })
        }
    }

