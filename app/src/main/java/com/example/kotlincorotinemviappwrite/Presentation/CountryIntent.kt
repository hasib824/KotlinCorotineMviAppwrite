package com.example.kotlincorotinemviappwrite.Presentation

import com.example.kotlincorotinemviappwrite.Domain.Country

sealed class CountryIntent {

    data class Success(val country: Country) : CountryIntent()
    data class Error(val message: String) : CountryIntent()
    object Loading : CountryIntent()
    data class getCountryByName(val name: String) : CountryIntent()
}