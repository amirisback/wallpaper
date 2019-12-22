package  com.frogobox.basewallpaperapp.ui.activity

import android.os.Bundle
import  com.frogobox.basewallpaperapp.R
import  com.frogobox.basewallpaperapp.base.admob.BaseAdmobActivity

class AboutUsActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setupDetailActivity("")
    }
}
