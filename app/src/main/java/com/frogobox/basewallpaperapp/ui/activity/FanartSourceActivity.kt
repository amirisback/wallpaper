package  com.frogobox.basewallpaperapp.ui.activity

import android.os.Bundle
import com.frogobox.basewallpaperapp.base.BaseActivity
import com.frogobox.basewallpaperapp.databinding.ActivityFanartSourceBinding
import  com.frogobox.basewallpaperapp.util.helper.ConstHelper
import kotlinx.android.synthetic.main.activity_fanart_source.*
import kotlinx.android.synthetic.main.ads_phone_tab_special_smart_banner.*

class FanartSourceActivity : BaseActivity<ActivityFanartSourceBinding>() {

    override fun setupViewBinding(): ActivityFanartSourceBinding {
        return ActivityFanartSourceBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        setupInfoCopyright()
        setupShowAdsBanner(ads_phone_tab_special_smart_banner)
    }

    private fun setupInfoCopyright() {
        val image = intent.getStringExtra(ConstHelper.Extra.EXTRA_FANART)
        val link = image?.split("/")
        if (link != null) {
            tv_base_url.text = link.get(0) + "//" + link.get(2)
        }
        tv_source_link.text = image
    }

}
