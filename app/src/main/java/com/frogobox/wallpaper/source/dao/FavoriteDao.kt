package  com.frogobox.wallpaper.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import  com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.util.helper.ConstHelper.RoomDatabase.ATTR_ID
import com.frogobox.wallpaper.util.helper.ConstHelper.RoomDatabase.ATTR_TABLE_ID
import  com.frogobox.wallpaper.util.helper.ConstHelper.RoomDatabase.TABLE_NAME_FAVORITE
import io.reactivex.Single

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 04/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 *  com.frogobox.wallpaper.source.dao
 *
 */
@Dao
interface FavoriteDao {

    @Query("SELECT * FROM $TABLE_NAME_FAVORITE")
    fun getAllData(): Single<List<Favorite>>

    @Insert
    fun insertData(data: Favorite)

    @Query("DELETE FROM $TABLE_NAME_FAVORITE WHERE $ATTR_TABLE_ID = :tableId")
    fun deleteDataFromTableId(tableId: Int)

    @Query("DELETE FROM $TABLE_NAME_FAVORITE WHERE $ATTR_ID = :id")
    fun deleteDataFromWallpaperId(id: Int)

    @Query("SELECT * FROM $TABLE_NAME_FAVORITE WHERE $ATTR_ID = :scriptId")
    fun searchData(scriptId: String): Single<List<Favorite>>

    @Query("DELETE FROM $TABLE_NAME_FAVORITE")
    fun nukeData()

}