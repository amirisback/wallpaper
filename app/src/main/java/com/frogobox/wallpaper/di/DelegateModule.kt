package com.frogobox.wallpaper.di

import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.wallpaper.ext.APP_PREF_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Faisal Amir on 08/01/23
 * Copyright (C) Frogobox
 */

val delegateModule = module {

    single {
        PreferenceDelegatesImpl(androidContext(), APP_PREF_NAME)
    }

}
