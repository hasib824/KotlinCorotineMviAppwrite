package com.example.kotlincorotinemviappwrite.Data

import com.example.kotlincorotinemviappwrite.Domain.Country

class getCountryBynameUsecase {

    suspend operator fun invoke(countryName: String) : Result<Country>
    {
        return MyRepository().getCountryByName(countryName)
    }

}