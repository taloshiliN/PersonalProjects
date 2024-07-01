package com.example.smartspendv6.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartspendv6.BottomNavigationBar
import kotlinx.coroutines.delay

val gradient = Brush.linearGradient(listOf(Color(0xFF4164BF), Color(0xFF85E299)))

@Composable
fun WalletScreen(navController: NavController) {
    BottomNavigationBar(navController = navController)
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Wallet Title
            Text(
                text = "Wallet",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 32.dp)
            )

            // Wallet Balance Card
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Wallet Balance",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "$2,000",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        GradientButton(
                            text = "Top Up",
                            icon = Icons.Filled.Add,
                            onClick = { /* Your button's action here */ },
                            gradient = gradient
                        )
                    }
                }
            }

            // Goals Section
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Goals",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Vacation Cards with Dynamic Progress
            VacationCard(goalName = "Vacations", amount = "1,000 USD", targetProgress = 0.75f)
            VacationCard(goalName = "Vacations", amount = "1,000 USD", targetProgress = 0.25f)
        }
    }
}

@Composable
fun GradientButton(text: String, icon: ImageVector, onClick: () -> Unit, gradient: Brush) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(48.dp)
            .clip(RoundedCornerShape(24.dp)), // Capsule shape
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .background(brush = gradient)
                .padding(horizontal = 24.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = icon, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = text, color = Color.White)
            }
        }
    }
}

// Vacation Card Composable with Dynamic Progress Bar
@Composable
fun VacationCard(goalName: String, amount: String, targetProgress: Float) {
    var progress by remember { mutableStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    LaunchedEffect(Unit) {
        while (progress < targetProgress) {
            delay(50)
            progress += 0.01f
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier.size(48.dp),
                color = Color(0xFF81C784),
                strokeWidth = 6.dp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = goalName,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Text(
                    text = amount,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color(0xFF4CAF50))
        }
    }
}

// Preview function
@Preview(showBackground = true)
@Composable
fun WalletScreenPreview() {
    val navController = rememberNavController()
    WalletScreen(navController)
}
