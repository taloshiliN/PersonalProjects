package com.example.smartspendv6.screen

//import androidx.compose.material3.HorizontalDivider
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartspendv6.BottomNavigationBar
import com.example.smartspendv6.R
import com.example.smartspendv6.components.GradientButton
import com.example.smartspendv6.data.Transfer
import com.example.smartspendv6.model.TransferViewModel

@Composable
fun TransferApp() {
    val navController = rememberNavController()
    val transferViewModel = viewModel<TransferViewModel>()

    NavHost(navController = navController, startDestination = "transfer_screen") {
        composable("transfer_screen")
        {
            Transfer_Screen(navController, transferViewModel)
        }
        composable("transfer_details_screen")
        {
            TransferDetailsScreen(navController, transferViewModel)
        }
    }
}

@Composable
fun Transfer_Screen(navController: NavHostController, transferViewModel: TransferViewModel) {
    BottomNavigationBar(navController = navController)
    val transfers = transferViewModel.transfers
    //val transfers = remember { mutableStateListOf<Transfer>() }

//    val selectedCategory = remember { mutableStateOf("All") }
//    val context = LocalContext.current
//
//    fun addTransfer(transaction: Transfer) {
//        transfers.add(transaction)
//        Toast.makeText(context, "Transferred successfully", Toast.LENGTH_SHORT).show()
//    }
//
//    fun filterTransfers(category: String): List<Transfer> {
//        return if (category == "All") {
//            transfers
//        } else {
//            transfers.filter { it.category == category }
//        }
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.width(100.dp))
                Text(
                    text = "Transfer",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(90.dp))
            }
            Spacer(modifier = Modifier.width(70.dp))
            Image(
                painter = painterResource(id = R.drawable.transfer),
                contentDescription = "transfer image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val gradient = Brush.linearGradient(listOf(Color(0xFF4164BF), Color(0xFF85E299)))
            GradientButton(
                text = "Make transfer",
                gradient = gradient,
                modifier = Modifier.size(width = 100.dp, height = 40.dp),
                onClick = { navController.navigate("transfer_details_screen") }
            )
            Spacer(modifier = Modifier.width(10.dp))

            GradientButton(
                text = "Transfer History",
                gradient = gradient,
                modifier = Modifier.size(width = 120.dp, height = 40.dp),
                onClick = { transfers[transfers.size - 1] }
            )
        }
            //HorizontalDivider()
        Spacer(modifier = Modifier.height(1.dp))


        Spacer(modifier = Modifier.padding(10.dp))

        //val filteredTransfers = filterTransfers(selectedCategory.value)

        LazyColumn {
            items(transfers) { transfer ->
                TransferRow(transfer = transfer)
                Spacer(modifier = Modifier.height(10.dp))
                    //HorizontalDivider()
            }
        }
    }
}

@Composable
fun TransferRow(transfer: Transfer) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Amount: ${transfer.amount}")
        Text(text = "Account/Phone: ${transfer.accountOrPhone}")
    }
}
@Preview(showBackground = true)
@Composable
fun TransferPreview(){
    val navController = rememberNavController()
    Transfer_Screen(navController = navController, transferViewModel = viewModel())
}