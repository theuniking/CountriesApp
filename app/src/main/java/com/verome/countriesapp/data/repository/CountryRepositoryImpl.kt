package com.verome.countriesapp.data.repository

import com.verome.countriesapp.data.mapper.toCountry
import com.verome.countriesapp.data.remote.CountryApi
import com.verome.countriesapp.domain.model.Country
import com.verome.countriesapp.domain.repository.CountryRepository
import com.verome.countriesapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi
):CountryRepository {

    override suspend fun getCountries(fetchImageFromRemote: Boolean): Flow<Resource<List<Country>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                emit(Resource.Success(data = api.getCountries().map { it.toCountry() }))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.message?:"Unexpected error occurred"))
            } finally {
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getCountryById(countryId: String): Resource<Country> {
        return try {
            val result = api.getCountryById(countryId)
            Resource.Success(result[0].toCountry())
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load country info"
            )
        }
    }
}