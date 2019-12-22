package com.frogobox.basewallpaperapp.ui.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import  com.frogobox.basewallpaperapp.R
import  com.frogobox.basewallpaperapp.base.ui.BaseFragment
import  com.frogobox.basewallpaperapp.base.view.BaseViewListener
import  com.frogobox.basewallpaperapp.ui.activity.FanartDetailActivity
import  com.frogobox.basewallpaperapp.util.helper.ConstHelper.Extra.EXTRA_FANART
import  com.frogobox.basewallpaperapp.util.helper.RawDataHelper
import  com.frogobox.basewallpaperapp.view.adapter.FanartViewAdapter
import kotlinx.android.synthetic.main.fragment_wallpaper.*

/**
 * A simple [Fragment] subclass.
 */
class WallpaperFragment : BaseFragment(), BaseViewListener<String> {

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
        setupShowAdsInterstitial()
    }

    private fun arrayFanArt(): MutableList<String> {
        return RawDataHelper().fetchData(context, R.raw._asset_image_fanart)
    }

    private fun setupAdapter(): FanartViewAdapter {
        val adapter = FanartViewAdapter()
        adapter.setupRequirement(this, arrayFanArt(), R.layout.item_grid_fanart)
        return adapter
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = setupAdapter()
    }

    override fun onItemClicked(data: String) {
        startActivity(
            Intent(context, FanartDetailActivity::class.java).putExtra(
                EXTRA_FANART,
                data
            )
        )
    }

    override fun onItemLongClicked(data: String) {

    }


}
