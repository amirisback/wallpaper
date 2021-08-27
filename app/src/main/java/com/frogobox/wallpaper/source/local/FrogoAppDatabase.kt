package  com.frogobox.wallpaper.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.frogobox.wallpaper.BuildConfig
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.source.dao.FavoriteDao
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
    ], version = 1
)


abstract class FrogoAppDatabase : RoomDatabase() {

    abstract fun favoriteScriptDao(): FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: FrogoAppDatabase? = null

        fun getInstance(context: Context): FrogoAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context): FrogoAppDatabase {
            return if (BuildConfig.DEBUG) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FrogoAppDatabase::class.java, DATABASE_NAME
                )
                    .addMigrations(MIGRATION_2_3)
                    .fallbackToDestructiveMigration() // FOR DEVELOPMENT ONLY !!!!
                    .build()
            } else {
                Room.databaseBuilder(
                    context.applicationContext,
                    FrogoAppDatabase::class.java, DATABASE_NAME
                )
                    .addMigrations(MIGRATION_2_3)
                    .build()
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}