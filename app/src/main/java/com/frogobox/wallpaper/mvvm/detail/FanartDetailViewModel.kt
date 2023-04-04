package com.frogobox.wallpaper.mvvm.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.wallpaper.core.BaseViewModel
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.source.WallpaperRepository

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
    private val repository: WallpaperRepository
) : BaseViewModel() {

    private var _favorite = MutableLiveData<Favorite>()
    var favorite: LiveData<Favorite> = _favorite

    private var _eventIsFavorite = MutableLiveData<Boolean>()
    var eventIsFavorite: LiveData<Boolean> = _eventIsFavorite

    fun saveFavorite(data: Favorite) {

        repository.saveRoomFavorite(data, object : FrogoStateResponse {
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

            override fun onSuccess() {
                _eventEmptyState.postValue(false)
                _eventIsFavorite.postValue(true)
            }
        })

    }

    fun deleteFavorite(tableId: Int) {

        repository.deleteRoomFromWallpaperID(tableId, object : FrogoStateResponse {
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

            override fun onSuccess() {
                _eventEmptyState.postValue(true)
                _eventIsFavorite.postValue(false)
            }
        })

    }

    fun getFavorite(id: Int) {

        repository.getRoomFavorite(object : FrogoDataResponse<List<Favorite>> {

            override fun onSuccess(data: List<Favorite>) {
                if (data.isEmpty()) {
                    _eventEmptyState.postValue(true)
                } else {
                    _eventEmptyState.postValue(false)
                    data.find { it.id == id }?.let {
                        _favorite.postValue(it)
                        _eventIsFavorite.postValue(true)
                    }
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
    
}
