package com.verome.countriesapp.presentation.countries_list

import com.verome.countriesapp.domain.model.Country

data class CountriesListState(
    val continents:Map<String,List<Country>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String = ""
)
