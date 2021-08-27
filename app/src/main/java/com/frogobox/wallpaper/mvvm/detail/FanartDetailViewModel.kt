package com.frogobox.wallpaper.mvvm.detail

import android.app.Application
import com.frogobox.sdk.core.FrogoLiveEvent
import com.frogobox.sdk.core.FrogoViewModel
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.source.FrogoRoomListener
import com.frogobox.wallpaper.source.FrogoDataRepository
import com.frogobox.wallpaper.source.FrogoDataSource

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
class FanartDetailViewModel(
    private val context: Application,
    private val repository: FrogoDataRepository
) : FrogoViewModel(context) {

    var favorite = FrogoLiveEvent<Favorite>()
    var eventIsFavorite = FrogoLiveEvent<Boolean>()

    fun saveFavorite(
        data: Favorite,
        callback: FrogoRoomListener
    ) {
        callback.onShowProgress()
        if (repository.saveRoomFavorite(data)) {
            callback.onHideProgress()
            callback.onSucces()
            eventEmptyData.postValue(false)
            eventIsFavorite.postValue(true)
        } else {
            callback.onHideProgress()
            callback.onFailed("Failed")
        }
    }

    fun deleteFavorite(tableId: Int, callback: FrogoRoomListener) {
        callback.onShowProgress()
        if (repository.deleteRoomFromWallpaperID(tableId)) {
            callback.onHideProgress()
            callback.onSucces()
            eventEmptyData.postValue(true)
            eventIsFavorite.postValue(false)
        } else {
            callback.onHideProgress()
            callback.onFailed("Failed")
        }
    }

    fun getFavorite(id: Int) {
        repository.getRoomFavorite(object :
            FrogoDataSource.GetRoomDataCallBack<List<Favorite>> {
            override fun onShowProgressDialog() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgressDialog() {
                eventShowProgress.postValue(false)
            }

            override fun onSuccess(data: List<Favorite>) {

                val tempFavoriteList = mutableListOf<Favorite>()
                tempFavoriteList.clear()
                tempFavoriteList.addAll(data)

                for (i in tempFavoriteList.indices) {
                    if (tempFavoriteList[i].id == id) {
                        eventEmptyData.postValue(false)
                        eventIsFavorite.postValue(true)
                        favorite.postValue(tempFavoriteList[i])
                        break
                    } else {
                        eventIsFavorite.postValue(false)
                        eventEmptyData.postValue(true)
                    }
                }
            }

            override fun onFinish() {}

            override fun onEmpty() {
                eventEmptyData.postValue(true)
                eventIsFavorite.postValue(false)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {}
        })
    }
    
}
