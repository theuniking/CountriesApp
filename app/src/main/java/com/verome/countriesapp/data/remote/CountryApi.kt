package com.verome.countriesapp.data.remote

import com.verome.countriesapp.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    @GET("/v3.1/all")
    suspend fun getCountries(): List<CountryDto>

    @GET("/v3.1/alpha/{countryId}")
    suspend fun getCountryById(@Path("countryId") countryId: String): List<CountryDto>
}