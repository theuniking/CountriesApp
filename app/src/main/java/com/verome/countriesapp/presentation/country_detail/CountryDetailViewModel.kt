package com.verome.countriesapp.presentation.country_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verome.countriesapp.domain.repository.CountryRepository
import com.verome.countriesapp.utils.Constants
import com.verome.countriesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _state = mutableStateOf(CountryDetailState())
    val state: State<CountryDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COUNTRY_ID)?.let { countryId ->
            getCountry(countryId)
        }
    }

    private fun getCountry(countryId:String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val countryInfoResult = async {countryRepository.getCountryById(countryId)}
            when(val result = countryInfoResult.await()) {
                is Resource.Success -> {
                    result.data?.let { country ->
                        _state.value = CountryDetailState(
                            country = country,
                        )
                    }
                }
                is Resource.Error -> _state.value = CountryDetailState(
                    error = result.message?:"Error"
                )

                else -> Unit
            }
        }
    }

}