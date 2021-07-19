package  com.frogobox.wallpaper.util

import android.content.Context
import androidx.preference.PreferenceManager
import  com.frogobox.wallpaper.source.FrogoDataRepository
import  com.frogobox.wallpaper.source.dao.FavoriteDao
import  com.frogobox.wallpaper.source.local.FrogoAppDatabase
import  com.frogobox.wallpaper.source.local.FrogoLocalDataSource
import  com.frogobox.wallpaper.source.remote.FrogoRemoteDataSource

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
object Injection {

    fun provideFrogoRepository(context: Context): FrogoDataRepository {

        val favoriteDao: FavoriteDao by lazy {
            FrogoAppDatabase.getInstance(context).favoriteScriptDao()
        }

        val appExecutors = AppExecutors()

        return FrogoDataRepository.getInstance(
            FrogoRemoteDataSource(context),
            FrogoLocalDataSource.getInstance(
                appExecutors,
                PreferenceManager.getDefaultSharedPreferences(context),
                favoriteDao
            )
        )
    }

}