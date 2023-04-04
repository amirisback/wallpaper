package com.frogobox.wallpaper.di

import com.frogobox.sdk.util.AppExecutors
import com.frogobox.wallpaper.source.LocalDataSource
import com.frogobox.wallpaper.source.RemoteDataSource
import com.frogobox.wallpaper.source.WallpaperDatabase
import com.frogobox.wallpaper.source.WallpaperRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


/**
 * Created by faisalamir on 05/03/22
 * PianoTiles
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

val repositoryModule = module {

    single {
        WallpaperDatabase.newInstance(androidContext()).favoriteDao()
    }

    single {
        LocalDataSource(AppExecutors(), get(), get())
    }

    single {
        RemoteDataSource(androidContext())
    }

    single {
        WallpaperRepository(get(), get())
    }

}