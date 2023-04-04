package com.frogobox.wallpaper.mvvm.wallpaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.sdk.ext.startActivityExt
import com.frogobox.wallpaper.core.BaseFragment
import com.frogobox.wallpaper.databinding.FragmentWallpaperBinding
import com.frogobox.wallpaper.databinding.ItemGridWallpaperBinding
import com.frogobox.wallpaper.model.Wallpaper
import com.frogobox.wallpaper.mvvm.detail.FanartDetailActivity
import com.frogobox.wallpaper.util.ConstHelper.Extra.EXTRA_FANART
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class WallpaperAssetFragment : BaseFragment<FragmentWallpaperBinding>() {

    private val mViewModel: WallpaperViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWallpaperBinding {
        return FragmentWallpaperBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        mViewModel.apply {
            wallpaper.observe(viewLifecycleOwner) {
                setupRV(it)
            }
        }
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedExt(view, savedInstanceState)
        if (savedInstanceState == null) {
            mViewModel.setFanArt(requireContext())
        }
    }

    private fun setupRV(data: List<Wallpaper>) {

        val callback = object : IFrogoBindingAdapter<Wallpaper, ItemGridWallpaperBinding> {
            override fun onItemClicked(
                binding: ItemGridWallpaperBinding,
                data: Wallpaper,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Wallpaper>
            ) {
                requireContext().startActivityExt<FanartDetailActivity, Wallpaper>(
                    EXTRA_FANART,
                    data
                )
            }

            override fun onItemLongClicked(
                binding: ItemGridWallpaperBinding,
                data: Wallpaper,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Wallpaper>
            ) {
            }

            override fun setViewBinding(parent: ViewGroup): ItemGridWallpaperBinding {
                return ItemGridWallpaperBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(
                binding: ItemGridWallpaperBinding,
                data: Wallpaper,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Wallpaper>
            ) {
                Glide.with(binding.root.context).load(data.linkImage).into(binding.ivPoster)
            }

        }

        binding.recyclerView.injectorBinding<Wallpaper, ItemGridWallpaperBinding>()
            .addData(data)
            .addCallback(callback)
            .createLayoutStaggeredGrid(2)
            .build()

    }

}
