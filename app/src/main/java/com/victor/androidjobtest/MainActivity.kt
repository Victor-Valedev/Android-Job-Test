package com.victor.androidjobtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victor.androidjobtest.adapter.ImgurImageAdapter
import com.victor.androidjobtest.api.RetrofitService
import com.victor.androidjobtest.databinding.ActivityMainBinding
import com.victor.androidjobtest.model.ResponseImgur
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val imgurAPI by lazy {
        RetrofitService.imgurAPI
    }

    private lateinit var imgurImageAdapter: ImgurImageAdapter

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        imgurImageAdapter = ImgurImageAdapter(listOf())
        imgurImageAdapter.notifyDataSetChanged()

        binding.rvList.adapter = imgurImageAdapter
        binding.rvList.layoutManager = GridLayoutManager(
            this,
            3,
            RecyclerView.VERTICAL,
            false
        )

    }

    override fun onStart() {
        super.onStart()
        recoveredImgurImages()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    private fun recoveredImgurImages() {

        job = CoroutineScope(Dispatchers.IO).launch {
            var response: Response<ResponseImgur>? = null

            try {
                response = imgurAPI.searchImageGallery("cats")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (response != null && response.isSuccessful) {

                val result = response.body()
                if (result != null) {

                    val list = result.data

                    val urlListImages = mutableListOf<String>()

                    list.forEach { data ->
                        val images = data.images
                        if (images != null && images.isNotEmpty()) {
                            val image = images[0]
                            val type = image.type
                            if (type == "image/jpeg") {
                                urlListImages.add(image.link)
                            }
                        }
                    }

                    withContext(Dispatchers.Main) {
                        imgurImageAdapter.addList(urlListImages)
                    }

                } else {
                    Log.i("info_imgur", "ERROR DATA API")
                }


            }
        }
    }
}