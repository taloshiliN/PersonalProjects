package com.example.smartspendv6.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smartspendv6.components.GradientButton
import com.example.smartspendv6.model.TransferViewModel

@Composable
fun TransferDetailsScreen(navController: NavHostController, transferViewModel: TransferViewModel) {
    val transfers = remember { mutableStateListOf<Pair<Double, String>>() }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (accountOrPhone, setAccountOrPhone) = remember { mutableStateOf("") }
    val context = LocalContext.current

    var amountError by remember { mutableStateOf<String?>(null) }
    var accountOrPhoneError by remember { mutableStateOf<String?>(null) }

    fun addTransfer(amount: Double, accountOrPhone: String) {
        transfers.add(amount to accountOrPhone) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Transfer Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = {
                setAmount(it)
                amountError = null
            },
            label = { Text("Amount") },
            isError = amountError != null
        )
        amountError?.let {
            Text(text = it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = accountOrPhone,
            onValueChange = {
                setAccountOrPhone(it)
                accountOrPhoneError = null
            },
            label = { Text("Account/Phone Number") },
            isError = accountOrPhoneError != null
        )
        accountOrPhoneError?.let {
            Text(text = it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        val gradient = Brush.linearGradient(listOf(Color(0xFF4164BF), Color(0xFF85E299)))

        GradientButton(
            text = "Transfer",
            gradient = gradient,
            modifier = Modifier.size(width = 100.dp, height = 40.dp),
            onClick = {
                val isAmountValid = try {
                    amount.toDouble()
                    true
                } catch (e: NumberFormatException) {
                    false
                }

                val isAccountOrPhoneValid = accountOrPhone.length <= 11 && accountOrPhone.all { it.isDigit() }

                if (amount.isBlank()) {
                    amountError = "Please enter the transfer amount"
                } else if (!isAmountValid) {
                    amountError = "Please enter a valid amount to transfer"
                }

                if (accountOrPhone.isBlank()) {
                    accountOrPhoneError = "Please enter account/phone number"
                } else if (!isAccountOrPhoneValid) {
                    accountOrPhoneError = "Please enter a valid account/phone number"
                }

                if (isAmountValid && isAccountOrPhoneValid) {
                    addTransfer(amount.toDouble(), accountOrPhone)
                    navController.popBackStack()
                    Toast.makeText(context, "Transfer Made", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}