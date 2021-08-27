package com.frogobox.wallpaper.mvvm.wallpaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.frogobox.wallpaper.core.BaseFragment
import com.frogobox.wallpaper.model.Wallpaper
import com.frogobox.wallpaper.mvvm.detail.FanartDetailActivity
import com.frogobox.wallpaper.util.ConstHelper.Extra.EXTRA_FANART
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.wallpaper.databinding.FragmentWallpaperBinding
import com.frogobox.wallpaper.databinding.ItemGridWallpaperBinding
import com.frogobox.wallpaper.mvvm.main.MainActivity

/**
 * A simple [Fragment] subclass.
 */
class WallpaperPixabayFragment : BaseFragment<FragmentWallpaperBinding>() {

    private lateinit var mViewModel: WallpaperPixabayViewModel

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentWallpaperBinding {
        return FragmentWallpaperBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainWallpaperPixabayViewModel().apply {

            searchImage()

            wallpaperListLive.observe(viewLifecycleOwner, {
                setupRV(it)
            })

        }
    }

    override fun setupUI(savedInstanceState: Bundle?) {
    }


    private fun setupRV(data: List<Wallpaper>) {

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
            ?.addData(data)
            ?.addCallback(callback)
            ?.createLayoutStaggeredGrid(2)
            ?.build()

    }

}