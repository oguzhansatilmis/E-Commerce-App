package com.android.ecommerceapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ecommerceapp.model.Result
import com.android.ecommerceapp.repository.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CommerceRepository
) : ViewModel() {

    private val _categoriesResponseState = MutableStateFlow<Result<Response<List<String>>>>(Result.Loading())
    val categoriesResponseState: StateFlow<Result<Response<List<String>>>> = _categoriesResponseState


     fun getAllCategories(){

        viewModelScope.launch {

           val categories =  repository.getCategories()


            categories?.let {response->
                if (categories.isSuccessful){
                    _categoriesResponseState.value = Result.Success(response)
                }
                else{
                    _categoriesResponseState.value = Result.Error("ErrorMessage",response)
                }

            }
        }

    }
    override fun onCleared() {
        super.onCleared()
        println("onCleared run")
    }
}