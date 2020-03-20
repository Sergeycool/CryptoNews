package com.example.cryptoapp.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.viewmodel.rate.HomeSharedViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragmentActivity
import com.ncapdevi.fragnav.FragNavController

class HomeActivity : BaseFragmentActivity<HomeSharedViewModel>(),
    FragNavController.TransactionListener, FragNavController.RootFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        sharedViewModel.eventNavigateToDetailCoin.observe(this@HomeActivity, Observer {
//            navigateToHomeRootPagerFragment()
//        })
    }

    override fun getRootFragment(index: Int): Fragment {
        TODO("Not yet implemented")
    }

    override fun onFragmentTransaction(
        fragment: Fragment?,
        transactionType: FragNavController.TransactionType
    ) {
        TODO("Not yet implemented")
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        TODO("Not yet implemented")
    }

    override val numberOfRootFragments: Int
        get() = TODO("Not yet implemented")

    fun navigateToHomeRootPagerFragment() {

    }

}