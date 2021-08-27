package com.frogobox.wallpaper.source

/*
 * Created by faisalamir on 28/08/21
 * Wallpaper
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
interface FrogoRoomListener {
    fun onShowProgress()
    fun onHideProgress()
    fun onSucces()
    fun onFailed(message: String)
}