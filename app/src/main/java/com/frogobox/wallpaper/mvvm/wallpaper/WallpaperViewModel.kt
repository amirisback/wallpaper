package com.frogobox.wallpaper.mvvm.wallpaper

import android.app.Application
import com.frogobox.wallpaper.base.BaseViewModel
import com.frogobox.wallpaper.model.Wallpaper
import com.frogobox.wallpaper.source.FrogoDataRepository
import com.frogobox.wallpaper.util.SingleLiveEvent

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseWallpaperApp
 * Copyright (C) 22/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.basewallpaperapp.viewmodel
 *
 */
class WallpaperViewModel (
    private val context: Application,
    private val repository: FrogoDataRepository
) :
    BaseViewModel(context) {

    var wallpaperListLive = SingleLiveEvent<List<Wallpaper>>()

}
