package com.verome.countriesapp.domain.model

data class Country(
    val countryId: String?,
    val flagUrl: String?,
    val name: String,
    val capital: String,
    val population: Int,
    val area: Int,
    val currencies: Pair<String,Currency>,
    val continent: String,
    val capitalCoordinates: String,
    val timeZones: List<String>,
    val mapUrl:String
)
