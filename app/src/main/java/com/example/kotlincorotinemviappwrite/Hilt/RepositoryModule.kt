package com.example.kotlincorotinemviappwrite.Hilt

import com.example.kotlincorotinemviappwrite.Data.MyNewRepository
import com.example.kotlincorotinemviappwrite.Data.MyRepository
import com.example.kotlincorotinemviappwrite.Domain.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @RealRepo
    abstract fun bindRepository(impl: MyRepository): RepositoryInterface

    @Binds
    @MockRepo
    abstract fun bindFakeRepository(impl: MyNewRepository): RepositoryInterface

    /*@Provides
    fun provideRepository(): RepositoryInterface {
        return MyRepository()
    }*/
}
