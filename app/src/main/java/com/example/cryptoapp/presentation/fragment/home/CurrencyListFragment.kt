package com.example.cryptoapp.presentation.fragment.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.cryptoapp.R
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.presentation.App
import com.example.cryptoapp.presentation.adapter.CoinInfoAdapter
import com.example.cryptoapp.presentation.viewmodel.home.CurrencyViewModel
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment
import kotlinx.android.synthetic.main.fragment_coin_list.*

class CurrencyListFragment : BaseFragment<CurrencyViewModel, HomeSharedViewModel>() {
    private val currencyAdapter = CoinInfoAdapter(App.context)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        currencyAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                sharedViewModel.navigateToDetailCoin(coinPriceInfo.fromSymbol)
            }
        }
        rv_coin_priceList.adapter = currencyAdapter
        viewModel.getLastPriceList()
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
