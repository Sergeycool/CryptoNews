package com.example.cryptoapp.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.fragment.home.CoinListFragment
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragmentActivity
import com.ncapdevi.fragnav.FragNavController
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseFragmentActivity<HomeSharedViewModel>(),
   FragNavController.RootFragmentListener,  FragNavController.TransactionListener {

    private lateinit var fragNavController: FragNavController
    lateinit var homeTabFragment: CoinListFragment
    lateinit var newsTabFragment: CoinListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeTabFragment = CoinListFragment.newInstance("Home fragment", "")
        newsTabFragment = CoinListFragment.newInstance("News fragment", "")
        fragNavController = FragNavController(supportFragmentManager, R.id.fragment_container)
        val rootFragments = listOf(
            homeTabFragment,
            newsTabFragment
        )
        initFragmentNavigation(rootFragments)
        fragNavController.initialize()

//        sharedViewModel.eventNavigateToDetailCoin.observe(this@HomeActivity, Observer {
//            navigateToHomeRootPagerFragment()
//        })
    }

    private fun initFragmentNavigation(listFragments: List<Fragment>) {
        with(fragNavController) {
            transactionListener = this@HomeActivity
//            rootFragmentListener = this@HomeActivity
            createEager = true
            fragmentHideStrategy = FragNavController.DETACH
            rootFragments = listFragments
//            navigationStrategy = UniqueTabHistoryStrategy(FragNavSwitchController{
//                switchTab()
//            })
        }
    }

    fun switchTab(tabId: Int) {
        val itemId = when (tabId) {
            FragNavController.TAB1 -> R.id.navigation_home
            FragNavController.TAB2 -> R.id.navigation_news
            else -> R.id.navigation_home
        }
        nav_view.selectedItemId = itemId
    }

    override fun getRootFragment(index: Int): Fragment {
        when(index) {
            FragNavController.TAB1 -> homeTabFragment
            FragNavController.TAB2 -> newsTabFragment
        }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun onFragmentTransaction(fragment: Fragment?,
                                       transactionType: FragNavController.TransactionType) {
        supportActionBar?.setDisplayHomeAsUpEnabled(!fragNavController.isRootFragment)
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(!fragNavController.isRootFragment)
    }

    override val numberOfRootFragments: Int = 2

    fun navigateToHomeRootPagerFragment() {

    }

}