package com.example.cryptoapp.presentation.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.fragment.home.CoinListFragment
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragmentActivity
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UniqueTabHistoryStrategy
import com.roughike.bottombar.BottomBar

class HomeActivity : BaseFragmentActivity<HomeSharedViewModel>(),
    FragNavController.RootFragmentListener, FragNavController.TransactionListener, BaseFragment.FragmentNavigation {

    private val fragNavController: FragNavController =
        FragNavController(supportFragmentManager, R.id.fragment_container)
    private lateinit var bottomBar: BottomBar
    override val numberOfRootFragments: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomBar = findViewById(R.id.bottom_bar)
        initFragmentNavigation(savedInstanceState)
//        sharedViewModel.eventNavigateToDetailCoin.observe(this@HomeActivity, Observer {
//            navigateToHomeRootPagerFragment()
//        })
    }

    private fun initFragmentNavigation(savedInstanceState: Bundle?) {
        fragNavController.apply {
            transactionListener = this@HomeActivity
            rootFragmentListener = this@HomeActivity
            createEager = true
            fragNavLogger = object : FragNavLogger {
                override fun error(message: String, throwable: Throwable) {
                    Log.e(TAG, message, throwable)
                }
            }
            fragmentHideStrategy = FragNavController.DETACH
            navigationStrategy = UniqueTabHistoryStrategy(
                object : FragNavSwitchController {
                    override fun switchTab(index: Int,
                                           transactionOptions: FragNavTransactionOptions?) {
                        bottomBar.selectTabAtPosition(index)
                    }
                })
        }
        fragNavController.initialize()
        val initial = savedInstanceState == null //TODO replace with mutable live data
        if (initial) {
            bottomBar.selectTabAtPosition(FragNavController.TAB1)
        }
        bottomBar.setOnTabSelectListener( { tabId ->
            when (tabId) {
                R.id.navigation_home -> fragNavController.switchTab(FragNavController.TAB1)
                R.id.navigation_news -> fragNavController.switchTab(FragNavController.TAB2)

            }
        }, initial)
        bottomBar.setOnTabReselectListener { fragNavController.clearStack() }
    }

    override fun onBackPressed() {
        if (fragNavController.popFragment().not()) {
            super.onBackPressed()
        }
    }

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            FragNavController.TAB1 -> CoinListFragment.newInstance("Home fragment", "")
            FragNavController.TAB2 -> CoinListFragment.newInstance("News fragment", "")
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    override fun pushFragment(fragment: Fragment, sharedElementList: List<Pair<View, String>>?) {
        val options = FragNavTransactionOptions.newBuilder()
        sharedElementList?.let {
            it.forEach { pair ->
                options.addSharedElement(pair)
            }
        }
        fragNavController.pushFragment(fragment, options.build())
    }

    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
        supportActionBar?.setDisplayHomeAsUpEnabled(!fragNavController.isRootFragment)
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(!fragNavController.isRootFragment)
    }

    fun navigateToHomeRootPagerFragment() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> fragNavController.popFragment()
        }
        return true
    }

    companion object {
        private val TAG = HomeActivity::class.java.simpleName
    }

}
