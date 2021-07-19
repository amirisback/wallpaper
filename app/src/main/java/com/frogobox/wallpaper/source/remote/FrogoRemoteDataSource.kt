package  com.frogobox.wallpaper.source.remote

import android.content.Context
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.source.FrogoDataSource
import com.frogobox.wallpaper.util.helper.FunHelper.Func.noAction

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.source.remote
 *
 */
class FrogoRemoteDataSource(private val context: Context) : FrogoDataSource {
    override fun saveRoomFavorite(data: Favorite): Boolean {
        return noAction()
    }

    override fun getRoomFavorite(callback: FrogoDataSource.GetRoomDataCallBack<List<Favorite>>) {
        noAction()
    }

    override fun deleteRoomFavorite(tableId: Int): Boolean {
        return noAction()
    }

    override fun deleteRoomFromWallpaperID(id: Int): Boolean {
        return noAction()
    }

    override fun nukeRoomFavorite(): Boolean {
        return noAction()
    }


}