package com.example.kotlincorotinemviappwrite.Presentation


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincorotinemviappwrite.Presentation.CountryReducer.reduce
import com.example.kotlincorotinemviappwrite.Domain.getCountryBynameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val getCountryBynameUsecase: getCountryBynameUsecase) : ViewModel() {

    public val _state = MutableStateFlow(CountryState())
    public val state : StateFlow<CountryState> = _state

    fun dispatch(onHandleInt: CountryIntent) {

        _state.update {
            reduce(onHandleInt, it)
        }

        when (onHandleInt) {
            is CountryIntent.getCountryByName -> {

                viewModelScope.launch {
                    getCountryByName(onHandleInt.name)
                }
            }
            is CountryIntent.Success -> { Log.e("Country result intent", onHandleInt.country.toString())}
            else -> Unit
        }
    }


    suspend fun getCountryByName(name: String)
    {
        val result  = getCountryBynameUsecase(name)

        result.onSuccess {
            println("Country result ${result.getOrNull().toString()}")
            Log.e("Country result", result.getOrNull().toString())

            withContext(Dispatchers.Main)
            {
               dispatch(CountryIntent.Success(result.getOrNull()!!))
            }
        }

        result.onFailure {

            Log.e("Country result", result.exceptionOrNull().toString())

            withContext(Dispatchers.Main)
            {
                dispatch(CountryIntent.Error(result.exceptionOrNull()?.message.toString()))
            }

        }



    }
}