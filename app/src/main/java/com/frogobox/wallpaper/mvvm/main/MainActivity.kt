package com.frogobox.wallpaper.mvvm.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.sdk.ext.startActivityExt
import com.frogobox.wallpaper.R
import com.frogobox.wallpaper.core.BaseActivity
import com.frogobox.wallpaper.databinding.ActivityMainBinding
import com.frogobox.wallpaper.mvvm.favorite.FavoriteFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupToolbar()
        setupBottomNav(R.id.framelayout_main_container)
        setupFragment(savedInstanceState)
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding.bottomNavMainMenu.selectedItemId = R.id.bottom_menu_wallpaper
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_about -> {
                startActivityExt<AboutUsActivity>()
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
                        supportActionBar?.title = getString(R.string.title_wallpaper)
                        setupChildFragment(frameLayout, MainWallpaperFragment())
                    }

                    R.id.bottom_menu_favorite -> {
                        supportActionBar?.title = getString(R.string.title_favorite)
                        setupChildFragment(frameLayout, FavoriteFragment())
                    }

                }

                true
            }
        }

    }
}
