package com.frogobox.basewallpaperapp.mvvm.wallpaper


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.basewallpaperapp.BuildConfig
import com.frogobox.basewallpaperapp.R
import com.frogobox.basewallpaperapp.base.BaseFragment
import com.frogobox.basewallpaperapp.base.BaseViewListener
import com.frogobox.basewallpaperapp.model.Wallpaper
import com.frogobox.basewallpaperapp.mvvm.detail.FanartDetailActivity
import com.frogobox.basewallpaperapp.util.helper.ConstHelper.Const.TYPE_MAIN_WALLPAPER
import com.frogobox.basewallpaperapp.util.helper.ConstHelper.Extra.EXTRA_FANART
import com.frogobox.frogopixabayapi.ConsumePixabayApi
import com.frogobox.frogopixabayapi.callback.PixabayResultCallback
import com.frogobox.frogopixabayapi.data.model.PixabayImage
import com.frogobox.frogopixabayapi.data.response.Response
import com.frogobox.frogopixabayapi.util.PixabayConstant
import kotlinx.android.synthetic.main.fragment_wallpaper.*

/**
 * A simple [Fragment] subclass.
 */
class WallpaperPixabayFragment : BaseFragment(), BaseViewListener<Wallpaper> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallpaper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupConsumePixabayApi(BuildConfig.TOPIC_WALLPAPER)
    }

    private fun arrayFanArt(pixabayApi: Response<PixabayImage>): MutableList<Wallpaper> {
        val arrayWallpaper = mutableListOf<Wallpaper>()
        for (i in pixabayApi.hits!!.indices) {
            arrayWallpaper.add(Wallpaper((TYPE_MAIN_WALLPAPER+i), pixabayApi.hits!![i].largeImageURL))
        }
        return arrayWallpaper
    }

    private fun setupAdapter(pixabayApi: Response<PixabayImage>): FanartViewAdapter {
        val adapter = FanartViewAdapter()
        adapter.setupRequirement(this, arrayFanArt(pixabayApi), R.layout.item_grid_wallpaper)
        return adapter
    }

    private fun setupRecyclerView(pixabayApi: Response<PixabayImage>) {
        recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = setupAdapter(pixabayApi)
    }

    private fun setupConsumePixabayApi(query: String){

        val consumePixabayApi = ConsumePixabayApi(PixabayConstant.SAMPLE_API_KEY)
        context?.let { consumePixabayApi.usingChuckInterceptor(it) }

        consumePixabayApi.searchImage(query,
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
                setupRecyclerView(data)
            }

            override fun onHideProgress() {
            }

            override fun onShowProgress() {
            }
        })

    }

    override fun onItemClicked(data: Wallpaper) {
        baseStartActivity<FanartDetailActivity, Wallpaper>(EXTRA_FANART, data)
    }

    override fun onItemLongClicked(data: Wallpaper) {

    }


}
