package com.frogobox.wallpaper.mvvm.wallpaper

import android.app.Application
import com.frogobox.api.pixabay.model.PixabayImage
import com.frogobox.api.pixabay.response.Response
import com.frogobox.sdk.core.FrogoLiveEvent
import com.frogobox.sdk.core.FrogoViewModel
import com.frogobox.wallpaper.model.Wallpaper
import com.frogobox.wallpaper.source.FrogoDataRepository
import com.frogobox.wallpaper.source.FrogoDataSource
import com.frogobox.wallpaper.util.helper.ConstHelper

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseWallpaperApp
 * Copyright (C) 22/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.wallpaper.viewmodel
 *
 */
class WallpaperPixabayViewModel(
    private val context: Application,
    private val repository: FrogoDataRepository
) :
    FrogoViewModel(context) {

    private val TOPIC_WALLPAPER = "Nature"

    var wallpaperListLive = FrogoLiveEvent<MutableList<Wallpaper>>()

    private fun arrayFanArt(pixabayApi: Response<PixabayImage>): MutableList<Wallpaper> {
        val arrayWallpaper = mutableListOf<Wallpaper>()
        for (i in pixabayApi.hits!!.indices) {
            arrayWallpaper.add(
                Wallpaper(
                    (ConstHelper.Const.TYPE_MAIN_WALLPAPER + i),
                    pixabayApi.hits!![i].largeImageURL
                )
            )
        }
        return arrayWallpaper
    }

    fun searchImage() {
        repository.searchImage(
            TOPIC_WALLPAPER,
            object : FrogoDataSource.GetResponseDataCallback<Response<PixabayImage>> {
                override fun onShowProgressDialog() {
                    eventShowProgress.postValue(true)
                }

                override fun onHideProgressDialog() {
                    eventShowProgress.postValue(false)
                }

                override fun onSuccess(data: Response<PixabayImage>) {
                    val listWallpaper = arrayFanArt(data)
                    wallpaperListLive.postValue(listWallpaper)
                }

                override fun onEmpty() {
                    eventEmptyData.postValue(true)
                }

                override fun onFinish() {}

                override fun onFailed(statusCode: Int, errorMessage: String?) {
                    eventFailed.postValue(errorMessage)
                }
            })
    }

}
