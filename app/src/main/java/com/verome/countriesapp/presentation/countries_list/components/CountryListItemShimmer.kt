package com.verome.countriesapp.presentation.countries_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.verome.countriesapp.presentation.utils.shimmerEffect

@Composable
fun CountryListItemShimmer() {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .width(82.dp)
                    .height(45.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect()
            )
            Spacer(
                modifier = Modifier
                    .width(12.dp)
            )
            Column(Modifier.weight(1f)) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(17.dp)
                        .shimmerEffect()
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(
                    modifier = Modifier
                        .height(6.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(14.dp)
                        .shimmerEffect()
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Icon(
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = "Dropdown icon"
            )

        }
    }

}