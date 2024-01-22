package com.android.ecommerceapp.ui.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ecommerceapp.model.Product
import com.android.ecommerceapp.model.Result
import com.android.ecommerceapp.repository.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: CommerceRepository
) :ViewModel() {

    private val _productResponseState = MutableStateFlow<Result<Response<List<Product>>>>(Result.Loading.create())
    val productResponseState :StateFlow<Result<Response<List<Product>>>> = _productResponseState

   fun getAllProduct(){

        viewModelScope.launch {

            try {
                repository.getAllProduct().collect{response->
                    if (response.isSuccessful){
                        _productResponseState.value = Result.Success(response)
                    }
                    else{

                        _productResponseState.value = Result.Error("${response.code()}")
                    }
                }
            }catch (e:Exception){
                Log.e("error","$e")
                _productResponseState.value= Result.Error("$e")
            }
        }

    }

}