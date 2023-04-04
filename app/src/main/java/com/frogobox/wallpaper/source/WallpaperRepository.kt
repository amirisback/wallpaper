package com.frogobox.wallpaper.source

import com.frogobox.coreapi.pixabay.model.PixabayImage
import com.frogobox.coreapi.pixabay.response.Response
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.sdk.source.FrogoRepository
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

class WallpaperRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : FrogoRepository(remoteDataSource, localDataSource), WallpaperDataSource {
    
    override fun searchImage(query: String, callback: FrogoDataResponse<Response<PixabayImage>>) {
        remoteDataSource.searchImage(query, callback)
    }

    override fun saveRoomFavorite(data: Favorite, callback: FrogoStateResponse) {
        localDataSource.saveRoomFavorite(data, callback)
    }

    override fun getRoomFavorite(callback: FrogoDataResponse<List<Favorite>>) {
        localDataSource.getRoomFavorite(callback)
    }

    override fun deleteRoomFavorite(tableId: Int, callback: FrogoStateResponse) {
        localDataSource.deleteRoomFavorite(tableId, callback)
    }

    override fun deleteRoomFromWallpaperID(id: Int, callback: FrogoStateResponse) {
        localDataSource.deleteRoomFromWallpaperID(id, callback)
    }

    override fun nukeRoomFavorite(callback: FrogoStateResponse) {
        localDataSource.nukeRoomFavorite(callback)
    }

}