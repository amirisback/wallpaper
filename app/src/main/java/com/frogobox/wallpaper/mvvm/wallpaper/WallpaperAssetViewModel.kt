package com.frogobox.wallpaper.mvvm.wallpaper

import android.app.Application
import com.frogobox.sdk.core.FrogoLiveEvent
import com.frogobox.sdk.core.FrogoViewModel
import com.frogobox.wallpaper.R
import com.frogobox.wallpaper.model.Wallpaper
import com.frogobox.wallpaper.source.FrogoDataRepository
import com.frogobox.wallpaper.util.helper.ConstHelper
import com.frogobox.wallpaper.util.helper.RawDataHelper

/*
 * Created by faisalamir on 19/07/21
 * BaseWallpaperApp
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
class WallpaperAssetViewModel(
    private val context: Application,
    private val repository: FrogoDataRepository
) :
    FrogoViewModel(context) {

    var wallpaperListLive = FrogoLiveEvent<MutableList<Wallpaper>>()

    fun setFanArt() {
        val arrayLinkImage = RawDataHelper().fetchData(context, R.raw._asset_darth_vader)

        val arrayWallpaper = mutableListOf<Wallpaper>()
        for (i in 0 until arrayLinkImage.size) {
            arrayWallpaper.add(Wallpaper((i + ConstHelper.Const.TYPE_MAIN_WALLPAPER), arrayLinkImage[i]))
        }

        wallpaperListLive.postValue(arrayWallpaper)
    }

}