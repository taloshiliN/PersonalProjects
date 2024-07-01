package com.example.smartspendv6

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartspendv6.data.Card
import com.example.smartspendv6.data.Goals
import com.example.smartspendv6.model.TransferViewModel
import com.example.smartspendv6.routes.Routes
import com.example.smartspendv6.screen.AddCard_Screen
import com.example.smartspendv6.screen.BillScreen
import com.example.smartspendv6.screen.Budget_Screen
//import com.example.smartspendv6.screen.HomeNavBar
import com.example.smartspendv6.screen.Home_Screen
import com.example.smartspendv6.screen.Login_Screen
import com.example.smartspendv6.screen.Signup_Screen
import com.example.smartspendv6.screen.TransferApp
import com.example.smartspendv6.screen.Transfer_Screen
import com.example.smartspendv6.screen.WalletScreen
import com.example.smartspendv6.sections.getGradient

@Composable
fun ToNavigate(){
    val navController = rememberNavController()
    val onAddNewGoal: (Goals) -> Unit
    val onDismiss: () -> Unit
    val onSave:(String,String)->Unit
    val userCards = listOf(
        Card(
            cardType = "",
            cardNumber = "",
            cardYear = "",
            cvv = "",
            balance = 0.0,
            color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
        )
    )
    val transferViewModel = TransferViewModel()
    NavHost(
        navController = navController,
        //suppose to be Routes.login
        startDestination = Routes.login,
        builder = {
            composable(Routes.login){
                Login_Screen(navController)
            }
            composable(Routes.signup){
                Signup_Screen(navController)
            }
            composable(Routes.home){
                Home_Screen(navController,onAddNewGoal = {},onDismiss = {},onSave = {a,b->},userCards = userCards)
            }
            composable(Routes.cardVerification){
                AddCard_Screen(navController)
            }
            composable(Routes.billsScreen){
                BillScreen(navController)
            }
            composable(Routes.budgetScreen){
                Budget_Screen(navController)
            }
            composable(Routes.walletScreen){
                WalletScreen(navController)
            }
            composable(Routes.transferScreen){
                Transfer_Screen(navController = navController, transferViewModel = transferViewModel)
            }
            composable(Routes.bottomNav){
                BottomNavigationBar(navController = navController)
            }
            composable(Routes.transferScreen){
                TransferApp()
            }
        })
}