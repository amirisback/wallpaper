package  com.frogobox.wallpaper.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frogobox.sdk.util.PagerHelper
import com.frogobox.wallpaper.core.BaseFragment
import com.frogobox.wallpaper.databinding.FragmentRootWallpaperBinding
import com.frogobox.wallpaper.mvvm.wallpaper.WallpaperAssetFragment
import com.frogobox.wallpaper.mvvm.wallpaper.WallpaperPixabayFragment

/**
 * A simple [Fragment] subclass.
 */
class MainWallpaperFragment : BaseFragment<FragmentRootWallpaperBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentRootWallpaperBinding {
        return FragmentRootWallpaperBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupShowAdsInterstitial()
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = PagerHelper(childFragmentManager)
        pagerAdapter.setupPagerFragment(WallpaperPixabayFragment(), "Pixabay")
        pagerAdapter.setupPagerFragment(WallpaperAssetFragment(), "Asset")
        binding?.apply {
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }

}
