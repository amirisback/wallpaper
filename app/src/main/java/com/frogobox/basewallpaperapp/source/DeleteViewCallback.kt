package  com.frogobox.basewallpaperapp.source

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * mvvm
 * Copyright (C) 16/11/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 *  com.frogobox.basewallpaperapp.modular.callback
 *
 */
interface DeleteViewCallback {
    fun onShowProgress()
    fun onHideProgress()
    fun onSuccesDelete()
    fun onFailed(message: String)
}