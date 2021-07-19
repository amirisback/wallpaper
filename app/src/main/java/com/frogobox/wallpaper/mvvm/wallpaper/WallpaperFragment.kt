package com.frogobox.wallpaper.mvvm.wallpaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.wallpaper.R
import com.frogobox.wallpaper.base.BaseFragment
import com.frogobox.wallpaper.databinding.FragmentWallpaperBinding
import com.frogobox.wallpaper.databinding.ItemGridWallpaperBinding
import com.frogobox.wallpaper.model.Wallpaper
import com.frogobox.wallpaper.mvvm.detail.FanartDetailActivity
import com.frogobox.wallpaper.util.helper.ConstHelper.Const.TYPE_MAIN_WALLPAPER
import com.frogobox.wallpaper.util.helper.ConstHelper.Extra.EXTRA_FANART
import com.frogobox.wallpaper.util.helper.RawDataHelper

/**
 * A simple [Fragment] subclass.
 */
class WallpaperFragment : BaseFragment<FragmentWallpaperBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentWallpaperBinding {
        return FragmentWallpaperBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupRV()
    }

    private fun arrayFanArt(): MutableList<Wallpaper> {
        val arrayLinkImage = RawDataHelper().fetchData(context, R.raw._asset_darth_vader)

        val arrayWallpaper = mutableListOf<Wallpaper>()
        for (i in 0 until arrayLinkImage.size) {
            arrayWallpaper.add(Wallpaper((i + TYPE_MAIN_WALLPAPER), arrayLinkImage[i]))
        }
        return arrayWallpaper
    }


    private fun setupRV() {

        val callback = object : IFrogoBindingAdapter<Wallpaper, ItemGridWallpaperBinding> {
            override fun onItemClicked(data: Wallpaper) {
                baseStartActivity<FanartDetailActivity, Wallpaper>(EXTRA_FANART, data)
            }

            override fun onItemLongClicked(data: Wallpaper) {
            }

            override fun setViewBinding(parent: ViewGroup): ItemGridWallpaperBinding {
                return ItemGridWallpaperBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(binding: ItemGridWallpaperBinding, data: Wallpaper) {
                Glide.with(binding.root.context).load(data.linkImage).into(binding.ivPoster)
            }

        }

        binding?.recyclerView?.injectorBinding<Wallpaper, ItemGridWallpaperBinding>()
            ?.addData(arrayFanArt())
            ?.addCallback(callback)
            ?.createLayoutStaggeredGrid(2)
            ?.build()

    }

}
