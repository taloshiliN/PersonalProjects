package com.example.smartspendv6.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartspendv6.BottomNavigationBar

@Composable
fun BillScreen(navController: NavController) {
    BottomNavigationBar(navController = navController)
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title Row with Back Icon and Filter Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Bills",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(6f)
                )
//                IconButton(onClick = { /* Handle filter action */ }) {
//                    Icon(
//                        imageVector = Icons.Default.FilterList,
//                        contentDescription = "Filter"
//                    )
//                }
            }

            // Tab Row for All, Paid, Unpaid
            TabRow(
                selectedTabIndex = 0,
                modifier = Modifier.padding(vertical = 8.dp),
                //   backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
                indicator = @Composable { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[0])
                            .padding(horizontal = 16.dp),
                        height = 4.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                Tab(
                    selected = true,
                    onClick = { /* Handle tab click */ },
                    text = { Text("All") }
                )
                Tab(
                    selected = false,
                    onClick = { /* Handle tab click */ },
                    text = { Text("Paid") }
                )
                Tab(
                    selected = false,
                    onClick = { /* Handle tab click */ },
                    text = { Text("Unpaid") }
                )
            }

            // List of Bills
            BillItem(date = "November 13, 2022", billName = "YouTube Premium", amount = "100", status = "Paid")
            BillItem(date = "November 13, 2022", billName = "Google Drive", amount = "170", status = "Paid")
            BillItem(date = "November 11, 2022", billName = "Food Panda", amount = "20", status = "Due on November 10, 2022")
            BillItem(date = "November 11, 2022", billName = "Water Bill", amount = "100", status = "Unpaid")
            BillItem(date = "November 11, 2022", billName = "Electricity", amount = "300", status = "Unpaid")
        }
    }
}

@Composable
fun BillItem(date: String, billName: String, amount: String, status: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
//                    Icon(
//                        imageVector = Icons.Default.Receipt,
//                        contentDescription = billName,
//                        tint = MaterialTheme.colorScheme.primary
//                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = billName,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                    Text(
                        text = status,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = amount,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = if (amount.startsWith("-")) Color.Red else Color.Green
                )
            }
        }
    }
}

// Preview function
@Preview(showBackground = true)
@Composable
fun BillScreenPreview() {
    val navController = rememberNavController()
    BillScreen(navController = navController)
}