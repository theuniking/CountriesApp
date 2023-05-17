package com.verome.countriesapp.presentation.countries_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.verome.countriesapp.presentation.countries_list.components.CountryListItem
import com.verome.countriesapp.presentation.countries_list.components.CountryListItemShimmer
import com.verome.countriesapp.presentation.utils.shimmerEffect

@Composable
fun CountriesListScreen(
    viewModel: CountriesListViewModel = hiltViewModel(),
    onCardClicked: (String?, String) -> Unit
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn() {
            if (state.isLoading) {
                item {
                    Column() {
                        Spacer(modifier = Modifier.height(24.dp))
                        Box(modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .height(20.dp)
                            .shimmerEffect()
                            .padding(top = 24.dp, start = 4.dp))
                    }
                }
                items(15) {
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )

                    CountryListItemShimmer()
                }
            } else {
                for ((continent, countries) in state.continents) {
                    item {
                        Text(
                            continent.uppercase(),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 4.dp, top = 24.dp)
                        )
                    }
                    items(countries.size) { countryPosition ->
                        CountryListItem(country = countries[countryPosition]) {
                            onCardClicked(it.countryId, it.name)
                        }
                    }
                }
            }

        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error, color = Color.Red, textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )

        }
    }
}