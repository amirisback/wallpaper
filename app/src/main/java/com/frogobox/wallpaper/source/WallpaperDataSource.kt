package  com.frogobox.wallpaper.source

import com.frogobox.coreapi.pixabay.model.PixabayImage
import com.frogobox.coreapi.pixabay.response.Response
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import  com.frogobox.wallpaper.model.Favorite

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 18/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 *  com.frogobox.wallpaper.source
 *
 */
interface WallpaperDataSource {

    fun searchImage(
        query: String,
        callback: FrogoDataResponse<Response<PixabayImage>>
    )

    // Room Database -------------------------------------------------------------------------------
    fun saveRoomFavorite(data: Favorite, callback: FrogoStateResponse)

    // Get
    fun getRoomFavorite(callback: FrogoDataResponse<List<Favorite>>)

    // Delete
    fun deleteRoomFavorite(tableId: Int, callback: FrogoStateResponse)
    fun deleteRoomFromWallpaperID(id: Int, callback: FrogoStateResponse)

    // Nuke
    fun nukeRoomFavorite(callback: FrogoStateResponse)

}