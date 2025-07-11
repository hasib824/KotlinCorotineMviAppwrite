package com.example.kotlincorotinemviappwrite.Hilt

import com.example.kotlincorotinemviappwrite.Constants.NetworkConstants
import com.example.kotlincorotinemviappwrite.Data.CountryApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl () = NetworkConstants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String) :
            Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


    @Provides
    @Singleton
    fun provideCountryApiService(retrofit: Retrofit) : CountryApiService = retrofit.create(CountryApiService::class.java)
}