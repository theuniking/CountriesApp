package com.verome.countriesapp.data.remote.dto

import com.verome.countriesapp.domain.model.Currency

data class CountryDto(
    val cca2: String?,
    val flags: Flags?,
    val name: Name?,
    val capital: List<String>?,
    val population: Int?,
    val area: Double?,
    val currencies: Map<String,Currency>?,
    val continents: List<String>?,
    val capitalInfo: CapitalInfo?,
    val timezones: List<String>?
)
data class Name(
    val common: String,
)
data class Flags(
    val png: String
)
data class CapitalInfo(
    val latlng: List<Double>?
)