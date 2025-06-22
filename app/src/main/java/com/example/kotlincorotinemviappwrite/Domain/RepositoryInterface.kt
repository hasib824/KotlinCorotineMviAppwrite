package com.example.kotlincorotinemviappwrite.Domain

interface RepositoryInterface {

    suspend fun getCountryByName(countryName: String) : Result<Country>
}