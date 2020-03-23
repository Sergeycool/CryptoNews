package com.example.cryptoapp.toolchain.mvvmbase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

@SuppressLint("Registered")
abstract class BaseFragmentActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var sharedViewModel: VM
//    private lateinit var fragNavController: FragNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProviders.of(this).get(getViewModelClass())
//        fragNavController = FragNavController(supportFragmentManager, R.id.fragment_container)
    }

//    protected fun clearFragmentStack() {
//        fragNavController.clearStack()
//    }

//    protected fun initFragmentNavigation() {
//        with(fragNavController){
//            transactionListener = this@BaseFragmentActivity
//            rootFragmentListener = this@BaseFragmentActivity
//            createEager = true
//            fragmentHideStrategy = FragNavController.DETACH
//        }
//    }

//    protected fun pushFragment(fragment: Fragment, sharedElementList: MutableList<Pair<View, String>>?) {
//        val options: FragNavTransactionOptions.Builder = FragNavTransactionOptions.newBuilder()
//        sharedElementList?.let {
//            sharedElementList.forEach { options.addSharedElement(it) }
//        }
//        fragNavController.pushFragment(fragment, options.build())
//    }

//    override val numberOfRootFragments: Int
//        get() = TODO("Not yet implemented")
//
//    override fun getRootFragment(index: Int): Fragment {
//        TODO("Not yet implemented")
//    }
//
//    override fun onFragmentTransaction(
//        fragment: Fragment?,
//        transactionType: FragNavController.TransactionType
//    ) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onTabTransaction(fragment: Fragment?, index: Int) {
//        TODO("Not yet implemented")
//    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
    }
}
