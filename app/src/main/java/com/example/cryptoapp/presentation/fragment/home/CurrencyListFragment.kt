package com.example.cryptoapp.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.viewmodel.home.CurrencyViewModel
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment
import kotlinx.android.synthetic.main.fragment_coin_list.*

private const val ARG_PARAM1 = "param1"

class CurrencyListFragment : BaseFragment<CurrencyViewModel, HomeSharedViewModel>() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin_list, container, false)
    } //TODO create abstract method

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        test_text?.let {
            it.text = param1
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_coin_list

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            CurrencyListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
