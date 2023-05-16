package com.verome.countriesapp.presentation.countries_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun CountryListDetailItem(label:String,content:String) {
    Text(text = buildAnnotatedString {
        append(label)
        withStyle(style = SpanStyle(color = Color.Black)) {
            append(content)
        }
    }, style = MaterialTheme.typography.labelMedium,
    modifier = Modifier.padding(vertical = 4.dp))
}