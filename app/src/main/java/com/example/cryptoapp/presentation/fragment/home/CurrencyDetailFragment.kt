package com.example.cryptoapp.presentation.fragment.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.UiConstants.ARGUMENT_FROM_SYMBOL
import com.example.cryptoapp.presentation.viewmodel.home.CurrencyDetailViewModel
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_currency_detail.*

class CurrencyDetailFragment : BaseFragment<CurrencyDetailViewModel, HomeSharedViewModel>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var text: String? = "BTC"
        text?.let { text = arguments?.getString(ARGUMENT_FROM_SYMBOL) }

        viewModel.getDetailInfo("BTC")

        viewModel.detailCurrencyInfo.observe(viewLifecycleOwner, Observer {
            tvPrice.text = it.price
            tvMinPrice.text = it.lowDay
            tvMaxPrice.text = it.highDay
            tvLastMarket.text = it.lastMarket
            tvLastUpdate.text = it.getFormattedTime()
            tvFromSymbol.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            Picasso.get()
                .load(it.getFullImageUrl())
                .into(ivLogoCoin)
        })
    }

    override fun getLayoutRes(): Int = R.layout.fragment_currency_detail

    companion object {
        @JvmStatic
        fun newInstance(fromSymbol: String) =
            CurrencyDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_FROM_SYMBOL, fromSymbol)
                }
            }

    }

}
