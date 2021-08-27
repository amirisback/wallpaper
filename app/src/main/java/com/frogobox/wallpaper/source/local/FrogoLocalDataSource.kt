package  com.frogobox.wallpaper.source.local

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.frogobox.api.pixabay.model.PixabayImage
import com.frogobox.api.pixabay.response.Response
import  com.frogobox.wallpaper.core.BaseCallback
import  com.frogobox.wallpaper.model.Favorite
import  com.frogobox.wallpaper.source.FrogoDataSource
import  com.frogobox.wallpaper.source.dao.FavoriteDao
import  com.frogobox.wallpaper.util.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
 * com.frogobox.publicspeakingbooster.source.local
 *
 */
class FrogoLocalDataSource private constructor(
    private val appExecutors: AppExecutors,
    private val sharedPreferences: SharedPreferences,
    private val favoriteDao: FavoriteDao
) : FrogoDataSource {

    override fun searchImage(
        query: String,
        callback: FrogoDataSource.GetResponseDataCallback<Response<PixabayImage>>
    ) {
    }

    override fun saveRoomFavorite(data: Favorite): Boolean {
        appExecutors.diskIO.execute {
            favoriteDao.insertData(data)
        }
        return true
    }

    override fun getRoomFavorite(callback: FrogoDataSource.GetRoomDataCallBack<List<Favorite>>) {
        appExecutors.diskIO.execute {
            favoriteDao.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseCallback<List<Favorite>>() {
                    override fun onCallbackSucces(data: List<Favorite>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        if (data.isEmpty()) {
                            callback.onEmpty()
                        }
                        callback.onHideProgressDialog()

                    }

                    override fun onCallbackError(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onAddSubscribe(disposable: Disposable) {
                        addSubscribe(disposable = disposable)
                    }

                    override fun onCompleted() {
                        callback.onHideProgressDialog()
                    }
                })
        }
    }

    override fun deleteRoomFavorite(tableId: Int): Boolean {
        appExecutors.diskIO.execute {
            favoriteDao.deleteDataFromTableId(tableId)
        }
        return true
    }

    override fun deleteRoomFromWallpaperID(id: Int): Boolean {
        appExecutors.diskIO.execute {
            favoriteDao.deleteDataFromWallpaperId(id)
        }
        return true
    }

    override fun nukeRoomFavorite(): Boolean {
        appExecutors.diskIO.execute {
            favoriteDao.nukeData()
        }
        return true
    }


    private
    var compositeDisposable: CompositeDisposable? = null

    fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()

            compositeDisposable?.add(disposable)
        }
    }

    /**
     * Clear all subscribers active in app
     */
    private fun clearSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable?.clear()
        }
    }

    companion object {

        private var INSTANCE: FrogoLocalDataSource? = null

        @JvmStatic
        fun getInstance(
            appExecutors: AppExecutors,
            sharedPreferences: SharedPreferences,
            favoriteDao: FavoriteDao

        ): FrogoLocalDataSource {
            if (INSTANCE == null) {
                synchronized(FrogoLocalDataSource::javaClass) {
                    INSTANCE = FrogoLocalDataSource(
                        appExecutors,
                        sharedPreferences,
                        favoriteDao
                    )
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

}
