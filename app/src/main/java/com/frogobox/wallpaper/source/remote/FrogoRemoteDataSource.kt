package  com.frogobox.wallpaper.source.remote

import android.content.Context
import com.frogobox.frogopixabayapi.ConsumePixabayApi
import com.frogobox.frogopixabayapi.callback.PixabayResultCallback
import com.frogobox.frogopixabayapi.data.model.PixabayImage
import com.frogobox.frogopixabayapi.data.response.Response
import com.frogobox.frogopixabayapi.util.PixabayConstant
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

    private val consumeApi = ConsumePixabayApi(PixabayConstant.SAMPLE_API_KEY)

    override fun searchImage(
        query: String,
        callback: FrogoDataSource.GetResponseDataCallback<Response<PixabayImage>>
    ) {
        consumeApi.usingChuckInterceptor(context)
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
            object : PixabayResultCallback<Response<PixabayImage>> {
                override fun failedResult(statusCode: Int, errorMessage: String?) {
                    callback.onFailed(statusCode, errorMessage)
                }

                override fun getResultData(data: Response<PixabayImage>) {
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