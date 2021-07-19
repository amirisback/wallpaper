package com.frogobox.basewallpaperapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.basewallpaperapp.R
import com.frogobox.basewallpaperapp.base.BaseFragment
import com.frogobox.basewallpaperapp.base.BaseViewListener
import com.frogobox.basewallpaperapp.model.Wallpaper
import com.frogobox.basewallpaperapp.ui.activity.FanartDetailActivity
import com.frogobox.basewallpaperapp.util.helper.ConstHelper.Const.TYPE_MAIN_WALLPAPER
import com.frogobox.basewallpaperapp.util.helper.ConstHelper.Extra.EXTRA_FANART
import com.frogobox.basewallpaperapp.util.helper.RawDataHelper
import com.frogobox.basewallpaperapp.view.adapter.FanartViewAdapter
import kotlinx.android.synthetic.main.fragment_wallpaper.*

/**
 * A simple [Fragment] subclass.
 */
class WallpaperFragment : BaseFragment(), BaseViewListener<Wallpaper> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallpaper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun arrayFanArt(): MutableList<Wallpaper> {
        val arrayLinkImage = RawDataHelper().fetchData(context, R.raw._asset_darth_vader)

        val arrayWallpaper = mutableListOf<Wallpaper>()
        for (i in 0 until arrayLinkImage.size) {
            arrayWallpaper.add(Wallpaper((i+TYPE_MAIN_WALLPAPER), arrayLinkImage[i]))
        }
        return arrayWallpaper
    }

    private fun setupAdapter(): FanartViewAdapter {
        val adapter = FanartViewAdapter()
        adapter.setupRequirement(this, arrayFanArt(), R.layout.item_grid_wallpaper)
        return adapter
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = setupAdapter()
    }

    override fun onItemClicked(data: Wallpaper) {
        baseStartActivity<FanartDetailActivity, Wallpaper>(EXTRA_FANART, data)
    }

    override fun onItemLongClicked(data: Wallpaper) {

    }


}
