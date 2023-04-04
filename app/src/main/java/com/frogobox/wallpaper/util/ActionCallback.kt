package com.frogobox.wallpaper.util

interface ActionCallback {

    fun onActionSuccess()

    fun onActionFailed(message: String)

}