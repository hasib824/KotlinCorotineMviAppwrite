package com.example.kotlincorotinemviappwrite.Domain

import com.example.kotlincorotinemviappwrite.Data.MyRepository
import com.example.kotlincorotinemviappwrite.Hilt.MockRepo
import javax.inject.Inject

class getCountryBynameUsecase @Inject constructor(@MockRepo val myRepository: RepositoryInterface) {

    suspend operator fun invoke(countryName: String) : Result<Country>
    {
        return myRepository.getCountryByName(countryName)
    }

}