package com.example.cryptoapp.presentation.fragment.home

import android.os.Bundle
import android.widget.Toast
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.App
import com.example.cryptoapp.presentation.UiConstants.ARGUMENT_FROM_SYMBOL
import com.example.cryptoapp.presentation.viewmodel.home.CurrencyDetailViewModel
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment

class CurrencyDetailFragment : BaseFragment<CurrencyDetailViewModel, HomeSharedViewModel>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var text: String? = "default"
        text?.let { text = arguments?.getString(ARGUMENT_FROM_SYMBOL) }

        Toast.makeText(App.context, text, Toast.LENGTH_LONG).show()
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
