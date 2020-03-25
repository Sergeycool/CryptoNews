package com.example.cryptoapp.toolchain.mvvmbase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

@SuppressLint("Registered")
abstract class BaseFragmentActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var sharedViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProviders.of(this).get(getViewModelClass())
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
    }
}
