package  com.frogobox.wallpaper.core

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * ImplementationAdmob
 * Copyright (C) 25/11/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 *  com.frogobox.wallpaper.activity
 *
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var mActivity: BaseActivity<*>

    protected var binding : VB? = null

    abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup): VB

    abstract fun setupViewModel()

    abstract fun setupUI(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = container?.let { setupViewBinding(inflater, it) }
        setupViewModel()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = (activity as BaseActivity<*>)
    }

    protected fun setupChildFragment(frameId: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    protected fun setupShowAdsInterstitial() {
        mActivity.setupShowAdsInterstitial()
    }

    fun <Model> baseNewInstance(argsKey: String, data: Model) {
        val argsData = Gson().toJson(data)
        val bundleArgs = Bundle().apply {
            putString(argsKey, argsData)
        }
        this.arguments = bundleArgs
    }

    protected inline fun <reified Model> baseGetInstance(argsKey: String): Model {
        val argsData = this.arguments?.getString(argsKey)
        return Gson().fromJson(argsData, Model::class.java)
    }

    protected fun checkArgument(argsKey: String): Boolean {
        return requireArguments().containsKey(argsKey)
    }

    protected fun setupEventEmptyView(view: View, isEmpty: Boolean) {
        if (isEmpty) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    protected fun setupEventProgressView(view: View, progress: Boolean) {
        if (progress) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected inline fun <reified ClassActivity> baseStartActivity() {
        context?.startActivity(Intent(context, ClassActivity::class.java))
    }

    protected inline fun <reified ClassActivity, Model> baseStartActivity(
        extraKey: String,
        data: Model
    ) {
        val intent = Intent(context, ClassActivity::class.java)
        val extraData = Gson().toJson(data)
        intent.putExtra(extraKey, extraData)
        this.startActivity(intent)
    }

}