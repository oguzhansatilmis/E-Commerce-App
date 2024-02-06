package com.android.ecommerceapp.ui.product

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.ecommerceapp.MainActivity
import com.android.ecommerceapp.base.BaseFragment
import com.android.ecommerceapp.base.BaseSecondaryFragment
import com.android.ecommerceapp.databinding.FragmentProductBinding
import com.android.ecommerceapp.model.Product
import com.android.ecommerceapp.model.Result
import com.android.ecommerceapp.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment :
    BaseSecondaryFragment<FragmentProductBinding, HomeViewModel,ProductViewModel, MainActivity>(FragmentProductBinding::inflate)
{
    override val viewModel by viewModels<HomeViewModel>()
    override val viewModel2 by viewModels<ProductViewModel>()

    private lateinit var adapter: ProductAdapter

    override fun onCreateFinished() {

    }

    override fun initializeListeners() {
        println("initializeListeners run")
    }

    override fun observeEvents() {
        println("observeEvents run")
        observeProduct()
    }

    private fun observeProduct() {

        println("Product Fragment")
        lifecycleScope.launch {

            viewModel.getAllCategories()

            viewModel.categoriesResponseState.collect{

                when(it){

                    is Result.Success->{
                        println("Success")
                        println("Product Fragment")
                        println(it.data?.body())
                    }
                    is Result.Loading->{
                        println("Loading")
                    }
                    is Result.Error->{
                        println("Error")
                        println(it.message)
                    }
                }

            }
        }

    }
    private fun initAdapter(productList: List<Product>) {

        adapter = ProductAdapter(productList)
        binding.apply {
            productRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
            productRecyclerview.adapter = adapter
        }
    }
}