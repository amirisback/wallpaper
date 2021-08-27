package  com.frogobox.wallpaper.util.helper

import android.Manifest
import android.os.Environment
import com.frogobox.wallpaper.BuildConfig

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.helper
 *
 */
object ConstHelper {

    object Const {
        const val TYPE_MAIN_WALLPAPER = 100
    }

    object Extra {
        const val BASE_EXTRA = BuildConfig.APPLICATION_ID
        const val EXTRA_OPTION = "$BASE_EXTRA.EXTRA_OPTION"
        const val EXTRA_FANART = "$BASE_EXTRA.EXTRA_FANART"
        const val EXTRA_FAV_FANART = "$BASE_EXTRA.EXTRA_FAV_FANART"
    }

    object RoomDatabase {
        val DATABASE_NAME = BuildConfig.APPLICATION_ID.replace(".", "_") + ".db"
        const val BASE_TABLE_NAME = "table"
        const val TABLE_NAME_DATA = "wallpaper_$BASE_TABLE_NAME"
        const val TABLE_NAME_FAVORITE = "favorite_$TABLE_NAME_DATA"

        const val ATTR_TABLE_ID = "table_id"
        const val ATTR_ID = "id"
        const val ATTR_LINK_IMAGE = "link_image"
    }

}