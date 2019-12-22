package com.frogobox.basewallpaperapp.viewmodel

import android.app.Application
import com.frogobox.basewallpaperapp.base.util.BaseViewModel
import com.frogobox.basewallpaperapp.model.Favorite
import com.frogobox.basewallpaperapp.modular.callback.DeleteViewCallback
import com.frogobox.basewallpaperapp.modular.callback.SaveViewCallback
import com.frogobox.basewallpaperapp.source.FrogoDataRepository
import com.frogobox.basewallpaperapp.source.FrogoDataSource
import com.frogobox.basewallpaperapp.util.SingleLiveEvent

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
 * com.frogobox.basewallpaperapp.viewmodel
 *
 */
class DetailViewModel(
    private val context: Application,
    private val repository: FrogoDataRepository
) : BaseViewModel(context) {

    var favorite = SingleLiveEvent<Favorite>()
    var eventIsFavorite = SingleLiveEvent<Boolean>()

    fun saveFavorite(
        data: Favorite,
        callback: SaveViewCallback
    ) {
        callback.onShowProgress()
        if (repository.saveRoomFavorite(data)) {
            callback.onHideProgress()
            callback.onSuccesInsert()
            eventIsEmpty.postValue(false)
            eventIsFavorite.postValue(true)
        } else {
            callback.onHideProgress()
            callback.onFailed("Failed")
        }
    }

    fun deleteFavorite(tableId: Int, callback: DeleteViewCallback) {
        callback.onShowProgress()
        if (repository.deleteRoomFavorite(tableId)) {
            callback.onHideProgress()
            callback.onSuccesDelete()
            eventIsEmpty.postValue(true)
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
                        eventIsEmpty.postValue(false)
                        eventIsFavorite.postValue(true)
                        favorite.postValue(tempFavoriteList[i])
                        break
                    } else {
                        eventIsFavorite.postValue(false)
                        eventIsEmpty.postValue(true)
                    }
                }
            }

            override fun onFinish() {}

            override fun onEmpty() {
                eventIsEmpty.postValue(true)
                eventIsFavorite.postValue(false)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {}
        })
    }
    
}
