package  com.frogobox.basewallpaperapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.frogobox.basewallpaperapp.R
import com.frogobox.basewallpaperapp.base.admob.BaseAdmobActivity
import com.frogobox.basewallpaperapp.model.Favorite
import com.frogobox.basewallpaperapp.model.Wallpaper
import com.frogobox.basewallpaperapp.modular.callback.DeleteViewCallback
import com.frogobox.basewallpaperapp.modular.callback.SaveViewCallback
import com.frogobox.basewallpaperapp.util.helper.ConstHelper.Extra.EXTRA_FANART
import com.frogobox.basewallpaperapp.util.helper.ConstHelper.Extra.EXTRA_FAV_FANART
import com.frogobox.basewallpaperapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_fanart_detail.*
import kotlinx.android.synthetic.main.ads_phone_tab_special_smart_banner.*

class FanartDetailActivity : BaseAdmobActivity(), SaveViewCallback,
    DeleteViewCallback {

    private lateinit var mViewModel: DetailViewModel
    private lateinit var extraFavorite: Favorite
    private lateinit var extraWallpaper: Wallpaper

    private var isFav = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fanart_detail)
        setupDetailActivity("")
        setupShowAdsBanner(ads_phone_tab_special_smart_banner)
        setupViewModel()
        setupExtraData()
    }

    private fun obtainDetailMovieViewModel(): DetailViewModel =
        obtainViewModel(DetailViewModel::class.java)

    private fun setupViewModel() {
        mViewModel = obtainDetailMovieViewModel().apply {

            favorite.observe(this@FanartDetailActivity, Observer {

            })

            eventIsFavorite.observe(this@FanartDetailActivity, Observer {
                setFavorite(it)
                isFav = it
            })

        }
    }

    private fun stateExtra(listenerMovie: () -> Unit, listenerFavMovie: () -> Unit) {
        if (checkExtra(EXTRA_FANART)) {
            listenerMovie()
        } else if (checkExtra(EXTRA_FAV_FANART)) {
            listenerFavMovie()
        }
    }


    private fun setupExtraData() {
        stateExtra({
            extraWallpaper = baseGetExtraData(EXTRA_FANART)
            extraWallpaper.linkImage?.let { setupImageView(it) }
            extraWallpaper.id.let { mViewModel.getFavorite(it) }
        }) {
            extraFavorite = baseGetExtraData(EXTRA_FAV_FANART)
            extraFavorite.linkImage?.let { setupImageView(it) }
            extraFavorite.id.let { mViewModel.getFavorite(it) }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state)
            menuItem?.getItem(1)?.icon = getDrawable(R.drawable.ic_star_favorite)
        else
            menuItem?.getItem(1)?.icon = getDrawable(R.drawable.ic_star_unfavorite)
    }

    private fun setupImageView(linkImage: String) {
        Glide.with(this).load(linkImage).into(iv_poster)
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
        menuItem = menu
        setFavorite(isFav)
        return true
    }

    private fun handleFavorite() {
        if (isFav) {
            stateExtra({
                extraWallpaper.id.let { mViewModel.deleteFavorite(it, this) }
                extraWallpaper.id.let { mViewModel.getFavorite(it) }
            }) {
                extraFavorite.id.let { mViewModel.deleteFavorite(it, this) }
                extraFavorite.id.let { mViewModel.getFavorite(it) }
            }
        } else {
            stateExtra({
                mViewModel.saveFavorite(
                    Favorite(
                        id = extraWallpaper.id,
                        linkImage = extraWallpaper.linkImage
                    ), this
                )
                extraWallpaper.id.let { mViewModel.getFavorite(it) }
            }) {
                mViewModel.saveFavorite(
                    Favorite(
                        id = extraFavorite.id,
                        linkImage = extraFavorite.linkImage
                    ), this
                )
                extraFavorite.id.let { mViewModel.getFavorite(it) }
            }

        }
        setFavorite(isFav)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_copyright -> {
                setupIntentData()
                true
            }
            R.id.toolbar_menu_favorite -> {
                handleFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onShowProgress() {}

    override fun onHideProgress() {}

    override fun onSuccesInsert() {
        showToast(getString(R.string.text_succes_add_favorite))
    }

    override fun onSuccesDelete() {
        showToast(getString(R.string.text_succes_delete_favorite))
    }

    override fun onFailed(message: String) {}

}
