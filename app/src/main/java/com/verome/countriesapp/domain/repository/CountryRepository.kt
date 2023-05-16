package com.verome.countriesapp.domain.repository

import com.verome.countriesapp.domain.model.Country
import com.verome.countriesapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getCountries(
        fetchImageFromRemote: Boolean,
    ): Flow<Resource<List<Country>>>
    suspend fun getCountryById(countryId: String): Resource<Country>
}