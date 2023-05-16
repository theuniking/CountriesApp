package com.verome.countriesapp.utils

sealed class Screen(val route: String) {
    object CountriesList: Screen("countries_list")
    object CountryDetail: Screen("country_detail")
}

