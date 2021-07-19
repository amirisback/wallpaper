package  com.frogobox.basewallpaperapp.mvvm.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.basewallpaperapp.R
import com.frogobox.basewallpaperapp.base.BaseFragment
import com.frogobox.basewallpaperapp.base.BaseViewListener
import com.frogobox.basewallpaperapp.model.Favorite
import com.frogobox.basewallpaperapp.mvvm.detail.FanartDetailActivity
import com.frogobox.basewallpaperapp.mvvm.main.MainActivity
import com.frogobox.basewallpaperapp.util.helper.ConstHelper.Extra.EXTRA_FAV_FANART
import kotlinx.android.synthetic.main.custom_view_empty.*
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

            eventIsEmpty.observe(viewLifecycleOwner, Observer {
                setupEventEmptyView(empty_view, it)
            })

        }
    }

    private fun setupRecyclerView(data: List<Favorite>) {
        val adapter = FavoriteViewAdapter()
        adapter.setupRequirement(this, data, R.layout.item_grid_wallpaper_fav)
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