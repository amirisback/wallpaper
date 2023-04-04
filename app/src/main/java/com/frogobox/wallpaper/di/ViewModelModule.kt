package com.frogobox.wallpaper.di

import com.frogobox.wallpaper.mvvm.detail.FanartDetailViewModel
import com.frogobox.wallpaper.mvvm.wallpaper.WallpaperViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

val viewModelModule = module {

    viewModel {
        WallpaperViewModel(get())
    }

    viewModel {
        FanartDetailViewModel(get())
    }

}