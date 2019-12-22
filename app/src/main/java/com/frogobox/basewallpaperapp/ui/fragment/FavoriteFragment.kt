package  com.frogobox.basewallpaperapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.basewallpaperapp.R
import com.frogobox.basewallpaperapp.base.ui.BaseFragment
import com.frogobox.basewallpaperapp.base.view.BaseViewListener
import com.frogobox.basewallpaperapp.model.Favorite
import com.frogobox.basewallpaperapp.ui.activity.FanartDetailActivity
import com.frogobox.basewallpaperapp.ui.activity.MainActivity
import com.frogobox.basewallpaperapp.util.helper.ConstHelper.Extra.EXTRA_FAV_FANART
import com.frogobox.basewallpaperapp.view.adapter.FavoriteViewAdapter
import com.frogobox.basewallpaperapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_wallpaper.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : BaseFragment(), BaseViewListener<Favorite> {

    private lateinit var mViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupViewModel()
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupShowAdsInterstitial()
        getFavorite()
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
    }

    private fun getFavorite() {
        mViewModel.getFavorite()
    }

    private fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainFavoriteViewModel().apply {

            favListLive.observe(viewLifecycleOwner, Observer {
                setupRecyclerView(it)
            })
        }
    }

    private fun setupRecyclerView(data: List<Favorite>) {
        val adapter = FavoriteViewAdapter()
        adapter.setupRequirement(this, data, R.layout.item_grid_fanart)
        recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = adapter
    }

    override fun onItemClicked(data: Favorite) {
        baseStartActivity<FanartDetailActivity, Favorite>(EXTRA_FAV_FANART, data)
    }

    override fun onItemLongClicked(data: Favorite) {

    }


}