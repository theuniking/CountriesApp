package com.verome.countriesapp.presentation.country_detail.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.verome.countriesapp.presentation.utils.shimmerEffect


@Composable
fun CountryDetailItem(modifier: Modifier = Modifier,label: String, content: String, isLoading: Boolean = true,) {
    Column() {
        Spacer(modifier =Modifier.height(24.dp))
        Row(modifier = modifier) {
            Canvas(
                modifier = Modifier
                    .size(size = (3 * 8).dp)
            ) {
                drawCircle(
                    color = Color.Black,
                    radius = 4.dp.toPx()
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column() {
                Text(text = label, style = MaterialTheme.typography.labelMedium)
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .height(17.dp)
                            .shimmerEffect()
                            .clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    Text(text = content, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}