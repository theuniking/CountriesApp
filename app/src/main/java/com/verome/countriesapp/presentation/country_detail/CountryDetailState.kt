package com.verome.countriesapp.presentation.country_detail

import com.verome.countriesapp.domain.model.Country

data class CountryDetailState(
    val isLoading:Boolean = false,
    val country: Country? = null,
    val error: String = ""
)
