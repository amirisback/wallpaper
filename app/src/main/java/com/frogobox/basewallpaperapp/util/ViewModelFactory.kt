package  com.frogobox.basewallpaperapp.util

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import  com.frogobox.basewallpaperapp.source.FrogoDataRepository
import com.frogobox.basewallpaperapp.viewmodel.DetailViewModel
import com.frogobox.basewallpaperapp.viewmodel.FavoriteViewModel
import com.frogobox.basewallpaperapp.viewmodel.WallpaperViewModel

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
 *  com.frogobox.basewallpaperapp.util
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

                isAssignableFrom(DetailViewModel::class.java) ->
                    DetailViewModel(mApplication, frogoDataRepository)

                isAssignableFrom(WallpaperViewModel::class.java) ->
                    WallpaperViewModel(mApplication, frogoDataRepository)

                isAssignableFrom(FavoriteViewModel::class.java) ->
                    FavoriteViewModel(mApplication, frogoDataRepository)

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