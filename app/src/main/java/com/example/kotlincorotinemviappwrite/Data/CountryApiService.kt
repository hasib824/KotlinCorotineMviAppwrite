package com.example.kotlincorotinemviappwrite.Data

// CountryApiService.kt
import com.example.kotlincorotinemviappwrite.Domain.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {

    @GET("v3.1/all")
    suspend fun getAllCountries(): Response<List<Country>>

    @GET("v3.1/name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): Response<List<Country>>


    @GET("v3.1/region/{region}")
    suspend fun getCountriesByRegion(@Path("region") region: String): Response<List<Country>>

    companion object {
        const val BASE_URL = "https://restcountries.com/"
    }
}