package com.example.kotlincorotinemviappwrite.Data

import android.util.Log
import com.example.kotlincorotinemviappwrite.Domain.Country
import com.example.kotlincorotinemviappwrite.Domain.RepositoryInterface
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MyRepository @Inject constructor(val countryApi : CountryApiService): RepositoryInterface {

    override suspend fun getCountryByName(countryName: String) : Result<Country>
    {
        val response = withContext(Dispatchers.IO)
         {
             countryApi.getCountryByName(countryName)
         }

           if(response.isSuccessful)
            {
                Log.e("Success", response.body().toString())
                return Result.success(response.body()!![0])

            }
            else
            {
               return   Result.failure(Exception("Failed to fetch country"))
            }

    }

    fun getAgeStatus() {


    }



}