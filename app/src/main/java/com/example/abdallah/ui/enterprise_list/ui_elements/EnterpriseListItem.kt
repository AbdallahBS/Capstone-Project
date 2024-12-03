package com.example.abdallah.ui.enterprise_list.ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.abdallah.domain.models.Enterprise

@Composable
fun EnterpriseListItem(
    enterprise: Enterprise,
    onItemClick: (Enterprise) -> Unit
) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onItemClick(enterprise) },
        elevation = androidx.compose.material3.CardDefaults.cardElevation(4.dp)
    ) {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Enterprise Image
            if (enterprise.images.isNotEmpty()) {
                androidx.compose.foundation.Image(
                    painter = rememberAsyncImagePainter(enterprise.images[0]),
                    contentDescription = enterprise.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                        .background(androidx.compose.ui.graphics.Color.Gray)
                )
            }

            // Enterprise Name
            Text(
                text = enterprise.name,
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Enterprise Short Description
            Text(
                text = enterprise.shortDescription,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Quick Actions
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                androidx.compose.material3.TextButton(onClick = {
                    // Navigate to LinkedIn
                }) {
                    Text(text = "LinkedIn")
                }
                androidx.compose.material3.TextButton(onClick = {
                    // Navigate to Website
                }) {
                    Text(text = "Website")
                }
            }
        }
    }
}
