package  com.frogobox.wallpaper.util

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import  com.frogobox.wallpaper.source.FrogoDataRepository
import com.frogobox.wallpaper.mvvm.detail.FanartDetailViewModel
import com.frogobox.wallpaper.mvvm.favorite.FavoriteViewModel
import com.frogobox.wallpaper.mvvm.wallpaper.WallpaperAssetViewModel
import com.frogobox.wallpaper.mvvm.wallpaper.WallpaperPixabayViewModel

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 26/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 *  com.frogobox.wallpaper.util
 *
 */
class ViewModelFactory private constructor(
    private val mApplication: Application,
    private val frogoDataRepository: FrogoDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {

                isAssignableFrom(FanartDetailViewModel::class.java) ->
                    FanartDetailViewModel(mApplication, frogoDataRepository)

                isAssignableFrom(FavoriteViewModel::class.java) ->
                    FavoriteViewModel(mApplication, frogoDataRepository)

                isAssignableFrom(WallpaperPixabayViewModel::class.java) ->
                    WallpaperPixabayViewModel(mApplication, frogoDataRepository)

                isAssignableFrom(WallpaperAssetViewModel::class.java) ->
                    WallpaperAssetViewModel(mApplication, frogoDataRepository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    mApplication,
                    Injection.provideFrogoRepository(mApplication.applicationContext)
                )
                    .also { INSTANCE = it }
            }
    }
}