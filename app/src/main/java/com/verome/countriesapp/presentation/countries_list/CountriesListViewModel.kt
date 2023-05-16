package com.verome.countriesapp.presentation.countries_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verome.countriesapp.domain.repository.CountryRepository
import com.verome.countriesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesListViewModel @Inject constructor(
    private val countryRepository: CountryRepository
): ViewModel() {

    private val _state = mutableStateOf(CountriesListState())
    val state: MutableState<CountriesListState> = _state

    init {
        getCountriesList()
    }

    private fun getCountriesList() {
        viewModelScope.launch {
            countryRepository
                .getCountries(fetchImageFromRemote = true)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                val continents = listings.groupBy { it.continent }
                                _state.value = state.value.copy(
                                    continents = continents
                                )
                            }
                        }
                        is Resource.Error -> _state.value = state.value.copy(
                            error = result.message?:"Error"
                        )
                        is Resource.Loading -> {
                            _state.value = state.value.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}