package  com.frogobox.basewallpaperapp.ui.activity

import android.os.Bundle
import com.frogobox.basewallpaperapp.base.BaseActivity
import com.frogobox.basewallpaperapp.databinding.ActivityAboutUsBinding

class AboutUsActivity : BaseActivity<ActivityAboutUsBinding>() {

    override fun setupViewBinding(): ActivityAboutUsBinding {
        return ActivityAboutUsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
    }

}
