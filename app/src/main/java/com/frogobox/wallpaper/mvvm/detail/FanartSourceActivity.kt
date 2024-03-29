package  com.frogobox.wallpaper.mvvm.detail

import android.os.Bundle
import com.frogobox.wallpaper.core.BaseActivity
import com.frogobox.wallpaper.databinding.ActivityFanartSourceBinding
import  com.frogobox.wallpaper.util.ConstHelper

class FanartSourceActivity : BaseActivity<ActivityFanartSourceBinding>() {

    override fun setupViewBinding(): ActivityFanartSourceBinding {
        return ActivityFanartSourceBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {

    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("")
        setupInfoCopyright()
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
