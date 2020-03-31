package com.example.cryptoapp.presentation.fragment.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.cryptoapp.R
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.presentation.App
import com.example.cryptoapp.presentation.adapter.CoinInfoAdapter
import com.example.cryptoapp.presentation.viewmodel.home.CurrencyListViewModel
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment
import kotlinx.android.synthetic.main.fragment_coin_list.*

class CurrencyListFragment : BaseFragment<CurrencyListViewModel, HomeSharedViewModel>() {
    private val currencyAdapter = CoinInfoAdapter(App.context)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        currencyAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                sharedViewModel.navigateToDetailCoin(coinPriceInfo.fromSymbol)
            }
        }
        rvCoinPriceList.adapter = currencyAdapter
        viewModel.coinDataList.observe(viewLifecycleOwner, Observer {
            currencyAdapter.coinInfoList = it
        })
    }

    override fun getLayoutRes(): Int = R.layout.fragment_coin_list

    companion object {
        @JvmStatic
        fun newInstance() = CurrencyListFragment()
    }

}
