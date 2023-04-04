package  com.frogobox.wallpaper.model

import androidx.annotation.Keep

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
 *  com.frogobox.wallpaper.model
 *
 */

@Keep
data class Wallpaper(
    var id: Int = 0,
    var linkImage: String? = ""
)