package com.verome.countriesapp.presentation.country_detail.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CountryDetailItem(label: String, content: String) {
    Row(modifier = Modifier.padding(top = 24.dp)) {
        Canvas(
            modifier = Modifier
                .size(size = (3*8).dp)
        ) {
            drawCircle(
                color = Color.Black,
                radius = 4.dp.toPx()
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column() {
            Text(text = label, style = MaterialTheme.typography.labelMedium)
            Text(text = content,style = MaterialTheme.typography.bodyLarge)
        }
    }
}