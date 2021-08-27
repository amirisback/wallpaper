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

    fun setHomeWallpaper(context: Context, linkImage: String): Boolean {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val wallpaperManager = WallpaperManager.getInstance(context)
        return try {
            val ins = URL(linkImage).openStream()
            wallpaperManager.setStream(ins)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun setLockScreenWallpaper(context: Context, linkImage: String): Boolean {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val wallpaperManager = WallpaperManager.getInstance(context)
        try {
            val ins = URL(linkImage).openStream()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setStream(ins, null, true, WallpaperManager.FLAG_LOCK)
            } else {
                Toast.makeText(
                    context,
                    "Lock screen walpaper not supported",
                    Toast.LENGTH_SHORT
                ).show()
            }
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }

    fun setHomeLockWallpaper(context: Context, linkImage: String): Boolean {
        return try {
            setHomeWallpaper(context, linkImage)
            setLockScreenWallpaper(context, linkImage)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

}