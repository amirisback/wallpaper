package com.frogobox.wallpaper.mvvm.wallpaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.frogobox.wallpaper.BuildConfig
import com.frogobox.wallpaper.base.BaseFragment
import com.frogobox.wallpaper.model.Wallpaper
import com.frogobox.wallpaper.mvvm.detail.FanartDetailActivity
import com.frogobox.wallpaper.util.helper.ConstHelper.Const.TYPE_MAIN_WALLPAPER
import com.frogobox.wallpaper.util.helper.ConstHelper.Extra.EXTRA_FANART
import com.frogobox.frogopixabayapi.ConsumePixabayApi
import com.frogobox.frogopixabayapi.callback.PixabayResultCallback
import com.frogobox.frogopixabayapi.data.model.PixabayImage
import com.frogobox.frogopixabayapi.data.response.Response
import com.frogobox.frogopixabayapi.util.PixabayConstant
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.wallpaper.databinding.FragmentWallpaperBinding
import com.frogobox.wallpaper.databinding.ItemGridWallpaperBinding

/**
 * A simple [Fragment] subclass.
 */
class WallpaperPixabayFragment : BaseFragment<FragmentWallpaperBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentWallpaperBinding {
        return FragmentWallpaperBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupConsumePixabayApi(BuildConfig.TOPIC_WALLPAPER)
    }

    private fun arrayFanArt(pixabayApi: Response<PixabayImage>): MutableList<Wallpaper> {
        val arrayWallpaper = mutableListOf<Wallpaper>()
        for (i in pixabayApi.hits!!.indices) {
            arrayWallpaper.add(Wallpaper((TYPE_MAIN_WALLPAPER+i), pixabayApi.hits!![i].largeImageURL))
        }
        return arrayWallpaper
    }

    private fun setupConsumePixabayApi(query: String){

        val consumePixabayApi = ConsumePixabayApi(PixabayConstant.SAMPLE_API_KEY)
        context?.let { consumePixabayApi.usingChuckInterceptor(it) }

        consumePixabayApi.searchImage(
            query,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            object : PixabayResultCallback<Response<PixabayImage>>{
            override fun failedResult(statusCode: Int, errorMessage: String?) {

            }

            override fun getResultData(data: Response<PixabayImage>) {
                setupRV(data)
            }

            override fun onHideProgress() {
            }

            override fun onShowProgress() {
            }
        })

    }

    private fun setupRV(pixabayApi: Response<PixabayImage>) {

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
            ?.addData(arrayFanArt(pixabayApi))
            ?.addCallback(callback)
            ?.createLayoutStaggeredGrid(2)
            ?.build()

    }

}