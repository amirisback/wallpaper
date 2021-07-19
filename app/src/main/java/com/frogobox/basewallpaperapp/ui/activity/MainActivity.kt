package com.frogobox.basewallpaperapp.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.basewallpaperapp.R
import com.frogobox.basewallpaperapp.base.BaseActivity
import com.frogobox.basewallpaperapp.databinding.ActivityMainBinding
import com.frogobox.basewallpaperapp.ui.fragment.FavoriteFragment
import com.frogobox.basewallpaperapp.ui.fragment.RootWallpaperFragment
import com.frogobox.basewallpaperapp.viewmodel.FavoriteViewModel
import com.frogobox.basewallpaperapp.viewmodel.WallpaperViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupToolbar()
        setupBottomNav(R.id.framelayout_main_container)
        setupFragment(savedInstanceState)
    }

    fun obtainWallpaperViewModel(): WallpaperViewModel =
        obtainViewModel(WallpaperViewModel::class.java)

    fun obtainFavoriteViewModel(): FavoriteViewModel =
        obtainViewModel(FavoriteViewModel::class.java)

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            bottom_nav_main_menu.selectedItemId = R.id.bottom_menu_wallpaper
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_about -> {
                baseStartActivity<AboutUsActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBottomNav(frameLayout: Int) {
        bottom_nav_main_menu.clearAnimation()
        bottom_nav_main_menu.itemIconTintList = null
        bottom_nav_main_menu.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.bottom_menu_wallpaper -> {
                    setupCustomTitleToolbar(R.string.title_wallpaper)
                    setupChildFragment(frameLayout, RootWallpaperFragment())
                }

                R.id.bottom_menu_favorite -> {
                    setupCustomTitleToolbar(R.string.title_favorite)
                    setupChildFragment(frameLayout, FavoriteFragment())
//                    setupShowAdsInterstitial()
                }

            }

            true
        }

    }
}
