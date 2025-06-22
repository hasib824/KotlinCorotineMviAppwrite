package com.example.kotlincorotinemviappwrite.Presentation

object CountryReducer {

    fun reduce(intent: CountryIntent, state: CountryState) : CountryState {
        when (intent) {
            is CountryIntent.Success -> return state.copy(country = intent.country, isLoading = false)
            is CountryIntent.Error -> return state.copy(error = intent.message, isLoading = false)
            is CountryIntent.Loading -> return state.copy(isLoading = true)
            is CountryIntent.getCountryByName -> return state

        }
    }
}