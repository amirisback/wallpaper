package  com.frogobox.wallpaper.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.frogobox.wallpaper.ext.APP_IS_DEBUG
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.util.ConstHelper.RoomDatabase.DATABASE_NAME

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 26/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 *  com.frogobox.wallpaper.source.local
 *
 */
@Database(
    entities = [
        (Favorite::class)
    ], version = 1,
    exportSchema = true
)
abstract class WallpaperDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {

        fun newInstance(context: Context): WallpaperDatabase {
            return buildDatabase(context)
        }

        private fun buildDatabase(context: Context): WallpaperDatabase {
            return if (APP_IS_DEBUG) {
                Room.databaseBuilder(context, WallpaperDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration() // FOR DEVELOPMENT ONLY !!!!
                    .build()
            } else {
                Room.databaseBuilder(context, WallpaperDatabase::class.java, DATABASE_NAME)
                    .build()
            }
        }

    }
}