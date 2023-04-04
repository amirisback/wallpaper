package  com.frogobox.wallpaper.mvvm.main

import android.os.Bundle
import com.frogobox.wallpaper.core.BaseActivity
import com.frogobox.wallpaper.databinding.ActivityAboutUsBinding

class AboutUsActivity : BaseActivity<ActivityAboutUsBinding>() {

    override fun setupViewBinding(): ActivityAboutUsBinding {
        return ActivityAboutUsBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("")
    }

}
