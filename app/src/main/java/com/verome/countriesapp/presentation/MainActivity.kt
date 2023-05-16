package com.verome.countriesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.verome.countriesapp.presentation.countries_list.CountriesListScreen
import com.verome.countriesapp.presentation.country_detail.CountryDetailScreen
import com.verome.countriesapp.presentation.ui.theme.CountriesAppTheme
import com.verome.countriesapp.utils.Constants
import com.verome.countriesapp.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentBackStackEntry = navController.currentBackStackEntryAsState().value?.destination?.route
            var currentCountry by remember { mutableStateOf("") }
            CountriesAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = if(currentBackStackEntry == Screen.CountriesList.route) "World countries"
                                    else currentCountry,
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            },
                            navigationIcon = {
                                if ((currentBackStackEntry
                                        ?: Screen.CountriesList.route).startsWith(Screen.CountryDetail.route)
                                ) {
                                    Row() {
                                        IconButton(onClick = {
                                            navController.navigateUp()
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.ArrowBack,
                                                contentDescription = null
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(17.dp))
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = Color.White
                            ),
                            modifier = Modifier
                                .drawWithContent {
                                    drawContent()
                                    clipRect {
                                        val strokeWidth = Stroke.DefaultMiter
                                        val y = size.height
                                        drawLine(
                                            brush = SolidColor(Color.LightGray),
                                            strokeWidth = strokeWidth,
                                            cap = StrokeCap.Square,
                                            start = Offset.Zero.copy(y = y),
                                            end = Offset(x = size.width, y = y)
                                        )
                                    }
                                },
                        )
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CountriesList.route,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(
                            route = Screen.CountriesList.route
                        ) {
                            CountriesListScreen { countryId, countryName ->
                                if (countryId != null) {
                                    navController.navigate(Screen.CountryDetail.route + "/${countryId}")
                                    currentCountry = countryName
                                }
                            }
                        }
                        composable(
                            route = Screen.CountryDetail.route + "/{${Constants.PARAM_COUNTRY_ID}}"
                        ) {
                            CountryDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
