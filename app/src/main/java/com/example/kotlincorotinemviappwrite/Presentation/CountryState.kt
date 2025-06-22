package com.example.kotlincorotinemviappwrite.Presentation

import com.example.kotlincorotinemviappwrite.Domain.Country

data class CountryState(val country: Country? = null, val isLoading: Boolean = true, val error: String? = null)