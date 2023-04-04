package com.frogobox.wallpaper.source

import com.frogobox.coreapi.pixabay.model.PixabayImage
import com.frogobox.coreapi.pixabay.response.Response
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.ext.executeRoomDB
import com.frogobox.sdk.ext.fetchRoomDB
import com.frogobox.sdk.source.FrogoLocalDataSource
import com.frogobox.sdk.util.AppExecutors
import com.frogobox.wallpaper.model.Favorite


/**
 * Created by faisalamir on 05/03/22
 * PianoTiles
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

class LocalDataSource(
    appExecutors: AppExecutors,
    sharedPreferences: PreferenceDelegatesImpl,
    private val favoriteDao: FavoriteDao
) : FrogoLocalDataSource(appExecutors, sharedPreferences), WallpaperDataSource {

    override fun searchImage(query: String, callback: FrogoDataResponse<Response<PixabayImage>>) {}

    override fun saveRoomFavorite(data: Favorite, callback: FrogoStateResponse) {
        favoriteDao.insertData(data).executeRoomDB(callback)
    }

    override fun getRoomFavorite(callback: FrogoDataResponse<List<Favorite>>) {
        favoriteDao.getAllData().fetchRoomDB(callback) {
            addSubscribe(it)
        }
    }

    override fun deleteRoomFavorite(tableId: Int, callback: FrogoStateResponse) {
        favoriteDao.deleteDataFromTableId(tableId).executeRoomDB(callback)
    }

    override fun deleteRoomFromWallpaperID(id: Int, callback: FrogoStateResponse) {
        favoriteDao.deleteDataFromWallpaperId(id).executeRoomDB(callback)
    }

    override fun nukeRoomFavorite(callback: FrogoStateResponse) {
        favoriteDao.nukeData().executeRoomDB(callback)
    }

}