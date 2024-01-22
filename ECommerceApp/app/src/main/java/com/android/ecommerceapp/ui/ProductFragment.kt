package com.android.ecommerceapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.android.ecommerceapp.R
import com.android.ecommerceapp.databinding.FragmentProductBinding
import com.android.ecommerceapp.model.Result
import com.android.ecommerceapp.ui.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private val productViewModel:ProductViewModel by viewModels()
    private var _binding :FragmentProductBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding= FragmentProductBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       lifecycleScope.launch {
            productViewModel.getAllProduct()

           val productResponse =productViewModel.productResponseState

            productResponse.collectLatest{result->

                when(result){
                    is Result.Success ->{
                        println("Success")

                        val dataBody = result.data.body()
                        dataBody?.let {
                            println(dataBody.toString())
                        }

                    }
                    is Result.Loading ->{

                        println("Loading")
                    }
                    is Result.Error ->{
                        println("Error")
                        println( result.message)
                    }
                }

            }




        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}