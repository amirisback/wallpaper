package  com.frogobox.wallpaper.mvvm.detail

import android.os.Bundle
import com.frogobox.wallpaper.base.BaseActivity
import com.frogobox.wallpaper.databinding.ActivityFanartSourceBinding
import  com.frogobox.wallpaper.util.helper.ConstHelper

class FanartSourceActivity : BaseActivity<ActivityFanartSourceBinding>() {

    override fun setupViewBinding(): ActivityFanartSourceBinding {
        return ActivityFanartSourceBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        setupInfoCopyright()
        setupShowAdsBanner(binding.ads.adsPhoneTabSpecialSmartBanner)
    }

    private fun setupInfoCopyright() {
        val image = intent.getStringExtra(ConstHelper.Extra.EXTRA_FANART)
        val link = image?.split("/")
        binding.apply {
            if (link != null) {
                tvBaseUrl.text = link.get(0) + "//" + link.get(2)
            }
            tvSourceLink.text = image
        }
    }

}
