package com.frogobox.wallpaper.source

import android.content.Context
import com.frogobox.api.pixabay.ConsumePixabayApi
import com.frogobox.coreapi.pixabay.PixabayUrl
import com.frogobox.coreapi.pixabay.model.PixabayImage
import com.frogobox.coreapi.pixabay.response.Response
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.sdk.source.FrogoRemoteDataSource
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

class RemoteDataSource(
    private val context: Context
) : FrogoRemoteDataSource(), WallpaperDataSource {

    override fun searchImage(query: String, callback: FrogoDataResponse<Response<PixabayImage>>) {
        val pixabayApi = ConsumePixabayApi(PixabayUrl.API_KEY)
        pixabayApi.usingChuckInterceptor(true, context)
        pixabayApi.searchImage(
            query,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            callback
        )
    }

    override fun saveRoomFavorite(data: Favorite, callback: FrogoStateResponse) {}

    override fun getRoomFavorite(callback: FrogoDataResponse<List<Favorite>>) {}

    override fun deleteRoomFavorite(tableId: Int, callback: FrogoStateResponse) {}

    override fun deleteRoomFromWallpaperID(id: Int, callback: FrogoStateResponse) {}

    override fun nukeRoomFavorite(callback: FrogoStateResponse) {}


}