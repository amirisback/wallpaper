package  com.frogobox.wallpaper.source

import com.frogobox.api.pixabay.model.PixabayImage
import com.frogobox.api.pixabay.response.Response
import  com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.source.callback.ResponseCallback

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
interface FrogoDataSource {

    fun searchImage(
        query: String,
        callback: GetResponseDataCallback<Response<PixabayImage>>
    )

    // Room Database -------------------------------------------------------------------------------
    fun saveRoomFavorite(data: Favorite): Boolean

    // Get
    fun getRoomFavorite(callback: GetRoomDataCallBack<List<Favorite>>)

    // Delete
    fun deleteRoomFavorite(tableId: Int): Boolean
    fun deleteRoomFromWallpaperID(id: Int): Boolean

    // Nuke
    fun nukeRoomFavorite(): Boolean

    // Get
    interface GetRoomDataCallBack<T> : ResponseCallback<T>

    // Get
    interface GetResponseDataCallback<T> : ResponseCallback<T>
}