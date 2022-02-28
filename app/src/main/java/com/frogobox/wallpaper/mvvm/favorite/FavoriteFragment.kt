package  com.frogobox.wallpaper.mvvm.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.wallpaper.core.BaseFragment
import com.frogobox.wallpaper.databinding.FragmentFavoriteBinding
import com.frogobox.wallpaper.databinding.ItemGridWallpaperFavBinding
import com.frogobox.wallpaper.model.Favorite
import com.frogobox.wallpaper.mvvm.detail.FanartDetailActivity
import com.frogobox.wallpaper.mvvm.main.MainActivity
import com.frogobox.wallpaper.util.ConstHelper.Extra.EXTRA_FAV_FANART

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private lateinit var mViewModel: FavoriteViewModel

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainFavoriteViewModel().apply {

            favListLive.observe(viewLifecycleOwner, {
                setupRV(it)
            })

            eventEmptyData.observe(viewLifecycleOwner, {
                binding?.empty?.emptyView?.let { it1 -> setupEventEmptyView(it1, it) }
            })

        }
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        getFavorite()
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
    }

    private fun getFavorite() {
        mViewModel.getFavorite()
    }

    private fun setupRV(data: List<Favorite>) {

        val callback = object : IFrogoBindingAdapter<Favorite, ItemGridWallpaperFavBinding> {
            override fun onItemClicked(
                binding: ItemGridWallpaperFavBinding,
                data: Favorite,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Favorite>
            ) {
                baseStartActivity<FanartDetailActivity, Favorite>(EXTRA_FAV_FANART, data)
            }

            override fun onItemLongClicked(
                binding: ItemGridWallpaperFavBinding,
                data: Favorite,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Favorite>
            ) {
            }

            override fun setViewBinding(parent: ViewGroup): ItemGridWallpaperFavBinding {
                return ItemGridWallpaperFavBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(
                binding: ItemGridWallpaperFavBinding,
                data: Favorite,
                position: Int,
                notifyListener: FrogoRecyclerNotifyListener<Favorite>
            ) {
                Glide.with(binding.root.context).load(data.linkImage).into(binding.ivPoster)
            }

        }

        binding?.recyclerView?.injectorBinding<Favorite, ItemGridWallpaperFavBinding>()
            ?.addData(data)
            ?.addCallback(callback)
            ?.createLayoutStaggeredGrid(2)
            ?.build()

    }

}