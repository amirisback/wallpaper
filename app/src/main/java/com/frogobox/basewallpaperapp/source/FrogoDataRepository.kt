package  com.frogobox.basewallpaperapp.source


import com.frogobox.basewallpaperapp.model.Favorite
import com.frogobox.basewallpaperapp.source.local.FrogoLocalDataSource
import com.frogobox.basewallpaperapp.source.remote.FrogoRemoteDataSource

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
 *  com.frogobox.basewallpaperapp.source
 *
 */
open class FrogoDataRepository(
    private val remoteDataSource: FrogoRemoteDataSource,
    private val localDataSource: FrogoLocalDataSource
) : FrogoDataSource {

    override fun saveRoomFavorite(data: Favorite): Boolean {
        return localDataSource.saveRoomFavorite(data)
    }


    override fun getRoomFavorite(callback: FrogoDataSource.GetRoomDataCallBack<List<Favorite>>) {
        return localDataSource.getRoomFavorite(callback)
    }

    override fun deleteRoomFavorite(tableId: Int): Boolean {
        return localDataSource.deleteRoomFavorite(tableId)
    }

    override fun deleteRoomFromWallpaperID(id: Int): Boolean {
        return localDataSource.deleteRoomFromWallpaperID(id)
    }

    override fun nukeRoomFavorite(): Boolean {
        return localDataSource.nukeRoomFavorite()
    }

    companion object {

        private var INSTANCE: FrogoDataRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param FrogoRemoteDataSource backend data source
         * *
         * @return the [FrogoRepository] instance
         */
        @JvmStatic
        fun getInstance(
            FrogoRemoteDataSource: FrogoRemoteDataSource,
            gitsLocalDataSource: FrogoLocalDataSource
        ) =
            INSTANCE ?: synchronized(FrogoDataRepository::class.java) {
                INSTANCE ?: FrogoDataRepository(FrogoRemoteDataSource, gitsLocalDataSource)
                    .also { INSTANCE = it }
            }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}