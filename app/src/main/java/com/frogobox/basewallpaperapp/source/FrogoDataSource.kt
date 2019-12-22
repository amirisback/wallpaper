package  com.frogobox.basewallpaperapp.source

import  com.frogobox.basewallpaperapp.base.data.BaseDataSource
import  com.frogobox.basewallpaperapp.model.Favorite
import  com.frogobox.basewallpaperapp.model.Wallpaper

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
 *  com.frogobox.basewallpaperapp.source
 *
 */
interface FrogoDataSource : BaseDataSource {

    // Room Database -------------------------------------------------------------------------------
    fun saveRoomFavorite(data: Favorite): Boolean

    // Get
    fun getRoomFavorite(callback: GetRoomDataCallBack<List<Favorite>>)

    // Delete
    fun deleteRoomFavorite(tableId: Int): Boolean
    fun deleteRoomFromWallpaperID(id: Int) : Boolean

    // Nuke
    fun nukeRoomFavorite(): Boolean

    // Get
    interface GetRoomDataCallBack<T> : BaseDataSource.ResponseCallback<T>
}