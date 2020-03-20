package com.example.cryptoapp.toolchain.mvvmbase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.R
import com.ncapdevi.fragnav.FragNavController
import java.lang.reflect.ParameterizedType

@SuppressLint("Registered")
open class BaseFragmentActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var sharedViewModel: VM
    protected lateinit var fragNavController: FragNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val classs: Class<VM> = getViewModelClass()

        sharedViewModel = ViewModelProviders.of(this).get(getViewModelClass())
        fragNavController = FragNavController(supportFragmentManager, R.id.fragment_container)
    }

    protected fun clearFragmentBackStack() {
        fragNavController.clearStack()
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }
}
