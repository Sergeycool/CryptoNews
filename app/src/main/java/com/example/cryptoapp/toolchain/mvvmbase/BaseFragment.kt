package com.example.cryptoapp.toolchain.mvvmbase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM : BaseViewModel, SVM : BaseViewModel> : Fragment() {
    protected lateinit var viewModel: VM
    protected lateinit var sharedViewModel: SVM
    lateinit var mFragmentNavigation: FragmentNavigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            mFragmentNavigation = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())
        sharedViewModel = ViewModelProviders.of(activity!!).get(getSharedViewModelClass())
        return inflater.inflate(getLayoutRes(), container, false)
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
    }

    @Suppress("UNCHECKED_CAST")
    private fun getSharedViewModelClass(): Class<SVM> {
        return (javaClass
            .genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<SVM>
    }

    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment, sharedElementList: List<Pair<View, String>>? = null)
    }

}
