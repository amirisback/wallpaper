package  com.frogobox.basewallpaperapp.util

import android.content.Context
import androidx.preference.PreferenceManager
import  com.frogobox.basewallpaperapp.source.FrogoDataRepository
import  com.frogobox.basewallpaperapp.source.dao.FavoriteDao
import  com.frogobox.basewallpaperapp.source.dao.WallpaperDao
import  com.frogobox.basewallpaperapp.source.local.FrogoAppDatabase
import  com.frogobox.basewallpaperapp.source.local.FrogoLocalDataSource
import  com.frogobox.basewallpaperapp.source.remote.FrogoRemoteDataSource

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
        val wallpaperDao: WallpaperDao by lazy {
            FrogoAppDatabase.getInstance(context).wallpaperDao()
        }

        val favoriteDao: FavoriteDao by lazy {
            FrogoAppDatabase.getInstance(context).favoriteScriptDao()
        }

        val appExecutors = AppExecutors()

        return FrogoDataRepository.getInstance(FrogoRemoteDataSource(context),
            FrogoLocalDataSource.getInstance(
                appExecutors,
                PreferenceManager.getDefaultSharedPreferences(context),
                wallpaperDao,
                favoriteDao))
    }

}