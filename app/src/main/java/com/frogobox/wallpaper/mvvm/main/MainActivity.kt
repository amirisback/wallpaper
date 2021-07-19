package com.frogobox.wallpaper.mvvm.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.wallpaper.R
import com.frogobox.wallpaper.core.BaseActivity
import com.frogobox.wallpaper.databinding.ActivityMainBinding
import com.frogobox.wallpaper.mvvm.favorite.FavoriteFragment
import com.frogobox.wallpaper.mvvm.favorite.FavoriteViewModel
import com.frogobox.wallpaper.mvvm.wallpaper.WallpaperAssetViewModel
import com.frogobox.wallpaper.mvvm.wallpaper.WallpaperPixabayViewModel

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

    fun obtainWallpaperAssetViewModel(): WallpaperAssetViewModel =
        obtainViewModel(WallpaperAssetViewModel::class.java)

    fun obtainWallpaperPixabayViewModel(): WallpaperPixabayViewModel =
        obtainViewModel(WallpaperPixabayViewModel::class.java)

    fun obtainFavoriteViewModel(): FavoriteViewModel =
        obtainViewModel(FavoriteViewModel::class.java)

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding.bottomNavMainMenu.selectedItemId = R.id.bottom_menu_wallpaper
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbarMain)
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
        binding.bottomNavMainMenu.apply {
            clearAnimation()
            itemIconTintList = null
            setOnNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.bottom_menu_wallpaper -> {
                        setupCustomTitleToolbar(R.string.title_wallpaper)
                        setupChildFragment(frameLayout, MainWallpaperFragment())
                    }

                    R.id.bottom_menu_favorite -> {
                        setupCustomTitleToolbar(R.string.title_favorite)
                        setupChildFragment(frameLayout, FavoriteFragment())
                    }

                }

                true
            }
        }

    }
}
