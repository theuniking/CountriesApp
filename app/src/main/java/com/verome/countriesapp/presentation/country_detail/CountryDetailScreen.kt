package com.verome.countriesapp.presentation.country_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.verome.countriesapp.presentation.country_detail.components.CountryDetailItem
import com.verome.countriesapp.presentation.utils.shimmerEffect

@Composable
fun CountryDetailScreen(
    viewModel: CountryDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize()) {

        if (state.error.isBlank()) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    modifier = if (state.isLoading) Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect()
                        .height(200.dp) else Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    model = state.country?.flagUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                CountryDetailItem(
                    label = "Capital:",
                    content = "${state.country?.capital}",
                    isLoading = state.isLoading
                )
                CountryDetailItem(
                    label = "Capital coordinates:",
                    modifier = Modifier.clickable {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(state.country?.mapUrl)))
                    },
                    content = "${state.country?.capitalCoordinates}",
                    isLoading = state.isLoading
                )
                CountryDetailItem(
                    label = "Population:",
                    content = "${state.country?.population}",
                    isLoading = state.isLoading
                )
                CountryDetailItem(
                    label = "Area:",
                    content = "${state.country?.area} km²",
                    isLoading = state.isLoading
                )
                CountryDetailItem(
                    label = "Currency:",
                    content = "${state.country?.currencies?.second?.name} (${state.country?.currencies?.second?.symbol}) (${state.country?.currencies?.first})",
                    isLoading = state.isLoading
                )
                CountryDetailItem(
                    label = "Region:",
                    content = "${state.country?.continent}",
                    isLoading = state.isLoading
                )
                CountryDetailItem(
                    label = "Timezones:",
                    content = "${state.country?.timeZones}",
                    isLoading = state.isLoading
                )
            }
        } else {
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