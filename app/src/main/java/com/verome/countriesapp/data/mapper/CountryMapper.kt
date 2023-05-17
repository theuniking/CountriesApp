package com.verome.countriesapp.data.mapper

import com.verome.countriesapp.data.remote.dto.CountryDto
import com.verome.countriesapp.domain.model.Country
import com.verome.countriesapp.domain.model.Currency
import kotlin.math.roundToInt

fun CountryDto.toCountry(): Country {
    return Country(
        countryId = cca2?.lowercase(),
        flagUrl = flags?.png,
        name = name?.common ?: "No name available",
        capital = capital?.get(0) ?: "No capital available",
        population = population ?: 0,
        area = area?.roundToInt() ?: (0),
        currencies = currencies?.entries?.toList()?.get(0)?.toPair() ?: Pair("", Currency("", "")),
        continent = continents?.get(0) ?: "No continent available",
        capitalCoordinates = capitalInfo?.latlng?.let { doubleToString(capitalInfo.latlng) } ?: "",
        timeZones = timezones ?: listOf(),
        mapUrl = maps?.googleMaps?:""
    )
}

private fun doubleToString(list: List<Double>): String {
    return buildString {
        append(list[0].toString().replace('.', '°'))
        append("',")
        append(list[1].toString().replace('.', '°'))
        append("'")

    }

}

