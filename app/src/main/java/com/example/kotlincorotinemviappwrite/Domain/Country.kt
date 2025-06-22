package com.example.kotlincorotinemviappwrite.Domain

data class Country(
    val name: Name,
    val capital: List<String>?,
    val population: Long,
    val region: String,
    val subregion: String?,
    val flags: Flags,
    val currencies: Map<String, Currency>?,
    val languages: Map<String, String>?,
    val area: Double?
)

data class Name(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>?
)

data class NativeName(
    val official: String,
    val common: String
)

data class Flags(
    val png: String,
    val svg: String,
    val alt: String?
)

data class Currency(
    val name: String,
    val symbol: String?
)