package com.android.ecommerceapp.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseSecondaryFragment<VB: ViewBinding,VM: ViewModel,VM2:ViewModel,A: Activity>(
    private val bindingInflater:(inflater: LayoutInflater)->VB) : Fragment(){

    private var _binding:VB?= null
    protected val binding :VB get() = _binding as VB

    protected abstract val viewModel:VM
    protected abstract val viewModel2:VM2
    protected abstract fun onCreateFinished()
    protected abstract fun initializeListeners()
    protected abstract fun observeEvents()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onCreateFinished()
        initializeListeners()
        observeEvents()
    }

    fun activity():A{
        return if (activity !=null){
            activity as A
        } else{activity as A}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("onDestroyView Run")
        _binding = null

    }
}