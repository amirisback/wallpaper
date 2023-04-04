package com.frogobox.wallpaper.util

import android.app.WallpaperManager
import android.content.Context
import android.os.Build
import android.os.StrictMode
import android.widget.Toast
import java.io.IOException
import java.net.URL

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
 * com.frogobox.wallpaper.util.helper
 *
 */
object WallpaperHelper {

    fun setHomeScreen(context: Context, res: Any, callback: ActionCallback) {
        val wallpaperManager = WallpaperManager.getInstance(context)
        when (res) {
            is Int -> {
                wallpaperManager.setResource(res)
                callback.onActionSuccess()
            }
            is String -> {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                try {
                    val ins = URL(res).openStream()
                    wallpaperManager.setStream(ins)
                    callback.onActionSuccess()
                } catch (e: IOException) {
                    e.printStackTrace()
                    callback.onActionFailed(e.message.toString())
                }
            }
        }
    }

    fun setLockScreen(context: Context, res: Any, callback: ActionCallback) {
        val wallpaperManager = WallpaperManager.getInstance(context)

        when (res) {
            is Int -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    wallpaperManager.setResource(res, WallpaperManager.FLAG_LOCK)
                    callback.onActionSuccess()
                } else {
                    callback.onActionFailed("Lock screen walpaper not supported")
                }
            }

            is String -> {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                try {
                    val linkImage = URL(res).openStream()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setStream(linkImage, null, true, WallpaperManager.FLAG_LOCK)
                        callback.onActionSuccess()
                    } else {
                        callback.onActionFailed("Lock screen walpaper not supported")
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    callback.onActionFailed(e.message.toString())
                }
            }

        }

    }

    fun setBothScreen(context: Context, res: Any, callback: ActionCallback) {
        val wallpaperManager = WallpaperManager.getInstance(context)

        when (res) {
            is Int -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    wallpaperManager.setResource(res)
                    wallpaperManager.setResource(res, WallpaperManager.FLAG_LOCK)
                    callback.onActionSuccess()
                } else {
                    callback.onActionFailed("Lock screen walpaper not supported")
                }
            }

            is String -> {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                try {
                    val linkImage = URL(res).openStream()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setStream(linkImage)
                        wallpaperManager.setStream(linkImage, null, true, WallpaperManager.FLAG_LOCK)
                        callback.onActionSuccess()
                    } else {
                        callback.onActionFailed("Lock screen walpaper not supported")
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    callback.onActionFailed(e.message.toString())
                }
            }

        }
    }

}