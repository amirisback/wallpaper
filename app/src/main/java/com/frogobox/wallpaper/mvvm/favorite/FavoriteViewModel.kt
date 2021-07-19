package com.frogobox.wallpaper.mvvm.favorite

import android.app.Application
import com.frogobox.wallpaper.core.BaseViewModel
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.source.FrogoDataRepository
import com.frogobox.wallpaper.source.FrogoDataSource
import com.frogobox.wallpaper.util.SingleLiveEvent

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
class FavoriteViewModel(
    private val context: Application,
    private val repository: FrogoDataRepository
) :
    BaseViewModel(context) {

    var favListLive = SingleLiveEvent<List<Favorite>>()

    fun getFavorite() {
        repository.getRoomFavorite(object :
            FrogoDataSource.GetRoomDataCallBack<List<Favorite>> {
            override fun onShowProgressDialog() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgressDialog() {
                eventShowProgress.postValue(false)
            }

            override fun onSuccess(data: List<Favorite>) {
                eventIsEmpty.postValue(false)
                favListLive.postValue(data)
            }

            override fun onFinish() {

            }

            override fun onEmpty() {
                eventIsEmpty.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })
    }
    
}
