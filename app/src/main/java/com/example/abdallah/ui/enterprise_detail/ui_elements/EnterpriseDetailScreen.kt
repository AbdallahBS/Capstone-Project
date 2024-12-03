package com.example.abdallah.ui.enterprise_detail.ui_elements

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.abdallah.ui.enterprise_detail.state.EnterpriseDetailViewModel

@OptIn(ExperimentalLayoutApi::class)

fun openUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage("com.android.chrome") // Specify Chrome browser package
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Chrome is not installed or cannot handle this URL", Toast.LENGTH_SHORT).show()
    }
}
@Composable
fun EnterpriseDetailScreen(
    viewModel: EnterpriseDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val enterpriseDetail = state.enterpriseDetail
    val context = LocalContext.current // Get context

    Box(modifier = Modifier.fillMaxSize()) {
        if (enterpriseDetail != null) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    // Enterprise name, email and description in a header style
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = enterpriseDetail.name,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Short description
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = enterpriseDetail.shortdescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Enterprise Image
                    if (enterpriseDetail.Images.isNotEmpty()) {
                        AsyncImage(
                            model = enterpriseDetail.Images[0],
                            contentDescription = "Enterprise Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surface)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    // Description
                    Text(
                        text = enterpriseDetail.description,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Additional Information: Subject, LinkedIn, etc.
                    Text(
                        text = "Subject: ${enterpriseDetail.sujet}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        text = "Location: ${enterpriseDetail.location}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    // Links to LinkedIn, Website, and PDF Books
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // LinkedIn button
                        OutlinedButton(
                            onClick = { openUrl(context, enterpriseDetail.linkedIn) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "LinkedIn")
                        }

                        // Website button
                        OutlinedButton(
                            onClick = { openUrl(context, enterpriseDetail.website) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Website")
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    // PFE Books with URL handling
                    if (enterpriseDetail.pfeBook2023.isNotEmpty()) {
                        Text(
                            text = "Consulter PFE Book 2023",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .clickable { openUrl(context, enterpriseDetail.pfeBook2023) }
                        )
                    }

                    if (enterpriseDetail.pfeBook2024.isNotEmpty()) {
                        Text(
                            text = " Consulter PFE Book 2024",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .clickable { openUrl(context, enterpriseDetail.pfeBook2024) }
                        )
                    }
                }
            }
        }

        // Error message
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Loading indicator
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
