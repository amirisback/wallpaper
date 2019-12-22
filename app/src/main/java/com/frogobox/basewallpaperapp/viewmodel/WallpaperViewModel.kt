package com.frogobox.basewallpaperapp.viewmodel

import android.app.Application
import com.frogobox.basewallpaperapp.base.util.BaseViewModel
import com.frogobox.basewallpaperapp.source.FrogoDataRepository

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


}
