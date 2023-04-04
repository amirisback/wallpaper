package com.frogobox.wallpaper.mvvm.wallpaper

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frogobox.coreapi.pixabay.model.PixabayImage
import com.frogobox.coreapi.pixabay.response.Response
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.util.FrogoFunc
import com.frogobox.wallpaper.R
import com.frogobox.wallpaper.core.BaseViewModel
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.model.Wallpaper
import com.frogobox.wallpaper.source.WallpaperRepository
import com.frogobox.wallpaper.util.ConstHelper

/**
 * Created by faisalamir on 19/07/21
 * BaseWallpaperApp
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
class WallpaperViewModel(
    private val repository: WallpaperRepository
) : BaseViewModel() {

    private val TOPIC_WALLPAPER = "Nature"
    
    private var _wallpaper = MutableLiveData<List<Wallpaper>>()
    var wallpaper: LiveData<List<Wallpaper>> = _wallpaper

    private var _wallpaperApi = MutableLiveData<List<Wallpaper>>()
    var wallpaperApi: LiveData<List<Wallpaper>> = _wallpaperApi

    private var _favorite = MutableLiveData<List<Favorite>>()
    var favorite: LiveData<List<Favorite>> = _favorite

    fun setFanArt(context: Context) {
        val arrayLinkImage = FrogoFunc.fetchRawData<String>(context, R.raw._asset_darth_vader)

        val arrayWallpaper = mutableListOf<Wallpaper>()
        for (i in 0 until arrayLinkImage.size) {
            arrayWallpaper.add(Wallpaper((i + ConstHelper.Const.TYPE_MAIN_WALLPAPER), arrayLinkImage[i]))
        }

        _wallpaper.postValue(arrayWallpaper)
    }

    fun searchImage() {
        repository.searchImage(
            TOPIC_WALLPAPER,
            object : FrogoDataResponse<Response<PixabayImage>> {
                
                override fun onSuccess(data: Response<PixabayImage>) {
                    _wallpaperApi.postValue(arrayFanArt(data))
                }

                override fun onFailed(statusCode: Int, errorMessage: String) {
                    _eventFailed.postValue(errorMessage)
                }

                override fun onFinish() {
                    _eventFinishState.postValue(true)
                }
                
                override fun onHideProgress() {
                    _eventShowProgressState.postValue(false)
                }

                override fun onShowProgress() {
                    _eventShowProgressState.postValue(true)
                }

            })
    }

    fun getFavorite() {
        repository.getRoomFavorite(object : FrogoDataResponse<List<Favorite>> {

            override fun onSuccess(data: List<Favorite>) {
                if (data.isEmpty()) {
                    _eventEmptyState.postValue(true)
                } else {
                    _eventEmptyState.postValue(false)
                    _favorite.postValue(data)
                }
            }

            override fun onFailed(statusCode: Int, errorMessage: String) {
                _eventFailed.postValue(errorMessage)
            }

            override fun onFinish() {
                _eventFinishState.postValue(true)
            }

            override fun onHideProgress() {
                _eventShowProgressState.postValue(false)
            }

            override fun onShowProgress() {
                _eventShowProgressState.postValue(true)
            }

        })
    }

    private fun arrayFanArt(pixabayApi: Response<PixabayImage>): MutableList<Wallpaper> {
        val data = mutableListOf<Wallpaper>()
        pixabayApi.hits?.forEach {
            data.add(Wallpaper((ConstHelper.Const.TYPE_MAIN_WALLPAPER + (it.id ?: 0)), it.webformatURL))
        }
        return data
    }

}