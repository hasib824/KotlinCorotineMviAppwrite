package com.example.kotlincorotinemviappwrite.Hilt

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockRepo
