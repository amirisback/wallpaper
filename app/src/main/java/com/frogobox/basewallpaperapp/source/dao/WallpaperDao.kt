package  com.frogobox.basewallpaperapp.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import  com.frogobox.basewallpaperapp.model.Wallpaper
import  com.frogobox.basewallpaperapp.util.helper.ConstHelper.RoomDatabase.TABLE_NAME_DATA
import io.reactivex.Single
/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 18/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 *  com.frogobox.basewallpaperapp.source.dao
 *
 */
@Dao
interface WallpaperDao {

    @Query("SELECT * FROM $TABLE_NAME_DATA")
    fun getAllData(): Single<List<Wallpaper>>

    @Insert
    fun insertData(wallpaper: Wallpaper)

    @Query("UPDATE $TABLE_NAME_DATA SET favorite = :favorite WHERE table_id = :tableid")
    fun updateFavorite(tableid: Int, favorite: Boolean)

    @Query("DELETE FROM $TABLE_NAME_DATA WHERE table_id = :tableid")
    fun deleteData(tableid: Int)

    @Query("DELETE FROM $TABLE_NAME_DATA")
    fun nukeData()

}