package com.frogobox.wallpaper

import android.content.Context
import com.frogobox.wallpaper.di.viewModelModule
import com.frogobox.sdk.FrogoKoinApplication
import com.frogobox.wallpaper.di.delegateModule
import com.frogobox.wallpaper.di.repositoryModule
import com.frogobox.wallpaper.ext.APP_IS_DEBUG
import org.koin.core.KoinApplication

class WallpaperApp : FrogoKoinApplication() {

    companion object {
        val TAG: String = WallpaperApp::class.java.simpleName

        lateinit var instance: FrogoKoinApplication

        fun getContext(): Context = instance.applicationContext
    }

    override fun setupKoinModule(koinApplication: KoinApplication) {
        koinApplication.modules(listOf(delegateModule, repositoryModule, viewModelModule))
    }

    override fun isDebugMode(): Boolean {
        return APP_IS_DEBUG
    }

    override fun onCreateExt() {
        super.onCreateExt()
        instance = this
    }
}
