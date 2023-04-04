package  com.frogobox.wallpaper.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frogobox.sdk.util.FrogoPagerHelper
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
        container: ViewGroup?
    ): FragmentRootWallpaperBinding {
        return FragmentRootWallpaperBinding.inflate(inflater, container, false)
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedExt(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = FrogoPagerHelper(childFragmentManager)
        pagerAdapter.addFragment(WallpaperPixabayFragment(), "Pixabay")
        pagerAdapter.addFragment(WallpaperAssetFragment(), "Asset")
        binding.apply {
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }

}
