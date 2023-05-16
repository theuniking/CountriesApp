package com.verome.countriesapp.presentation.country_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.verome.countriesapp.presentation.country_detail.components.CountryDetailItem

@Composable
fun CountryDetailScreen(
    viewModel: CountryDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.country != null) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    model = state.country.flagUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                CountryDetailItem(label = "Capital:", content = state.country.capital)
                CountryDetailItem(
                    label = "Capital coordinates:",
                    content = state.country.capitalCoordinates
                )
                CountryDetailItem(label = "Population:", content = "${state.country.population}")
                CountryDetailItem(label = "Area:", content = "${state.country.area} km²")
                CountryDetailItem(label = "Currency:", content = "${state.country.currencies.second.name} (${state.country.currencies.second.symbol}) (${state.country.currencies.first})")
                CountryDetailItem(label = "Region:", content = state.country.continent)
                CountryDetailItem(label = "Timezones:", content = "${state.country.timeZones}")
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
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}