package  com.frogobox.basewallpaperapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import  com.frogobox.basewallpaperapp.R
import  com.frogobox.basewallpaperapp.base.admob.BaseAdmobActivity
import  com.frogobox.basewallpaperapp.util.helper.ConstHelper.Extra.EXTRA_FANART
import kotlinx.android.synthetic.main.activity_fanart_detail.*
import kotlinx.android.synthetic.main.ads_phone_tab_special_smart_banner.*

class FanartDetailActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fanart_detail)
        setupDetailActivity("")
        setupImageView()
        setupShowAdsBanner(ads_phone_tab_special_smart_banner)
    }

    private fun setupImageView() {
        val image = intent.getStringExtra(EXTRA_FANART)
        Glide.with(this).load(image).into(iv_poster)
    }

    private fun setupIntentData() {
        val image = intent.getStringExtra(EXTRA_FANART)
        startActivity(
            Intent(
                this,
                FanartSourceActivity::class.java
            ).putExtra(EXTRA_FANART, image)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_copyright -> {
                setupIntentData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
