package  com.frogobox.wallpaper.source.remote

import android.content.Context
import com.frogobox.api.core.ConsumeApiResponse
import com.frogobox.api.pixabay.ConsumePixabayApi
import com.frogobox.api.pixabay.model.PixabayImage
import com.frogobox.api.pixabay.response.Response
import com.frogobox.api.pixabay.util.PixabayUrl
import com.frogobox.sdk.core.FrogoFunc.noAction
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.source.FrogoDataSource

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

    private val consumeApi = ConsumePixabayApi(PixabayUrl.API_KEY)

    override fun searchImage(
        query: String,
        callback: FrogoDataSource.GetResponseDataCallback<Response<PixabayImage>>
    ) {
        consumeApi.searchImage(
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
            object : ConsumeApiResponse<Response<PixabayImage>> {
                override fun onFailed(statusCode: Int, errorMessage: String?) {
                    callback.onFailed(statusCode, errorMessage)
                }

                override fun onSuccess(data: Response<PixabayImage>) {
                    callback.onSuccess(data)
                }

                override fun onHideProgress() {
                    callback.onHideProgressDialog()
                }

                override fun onShowProgress() {
                    callback.onShowProgressDialog()
                }
            }
        )
    }


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