package  com.frogobox.wallpaper.model

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frogobox.wallpaper.util.ConstHelper.RoomDatabase.ATTR_ID
import com.frogobox.wallpaper.util.ConstHelper.RoomDatabase.ATTR_LINK_IMAGE
import com.frogobox.wallpaper.util.ConstHelper.RoomDatabase.ATTR_TABLE_ID
import  com.frogobox.wallpaper.util.ConstHelper.RoomDatabase.TABLE_NAME_FAVORITE

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
 *  com.frogobox.wallpaper.model
 *
 */

@Keep
@Entity(tableName = TABLE_NAME_FAVORITE)
data class Favorite(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ATTR_TABLE_ID)
    var table_id: Int = 0,

    @ColumnInfo(name = ATTR_ID)
    var id: Int = 0,

    @ColumnInfo(name = ATTR_LINK_IMAGE)
    var linkImage: String? = ""

)