package com.example.smartspendv6

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.smartspendv6.routes.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavController){
    val coroutineScope = rememberCoroutineScope()

    val items = listOf(
        BottomNavigation(
            title = "Home",
            selectedIcon = painterResource(id = R.drawable.homefilled_24px),
            unselectedIcon = painterResource(id = R.drawable.home_24px),
            hasNews = false,
            onClick = {
                coroutineScope.launch {
                    navController.navigate(Routes.home){
                        popUpTo(Routes.home){
                            inclusive = true
                        }
                    }
                }
            }
        ),
        BottomNavigation(
            title = "Transfer",
            selectedIcon = painterResource(id = R.drawable.paidfilled_24px),
            unselectedIcon = painterResource(id = R.drawable.paid_24px),
            hasNews = false,
            onClick = {
                coroutineScope.launch {
                    navController.navigate(Routes.transferScreen)
                }
            }
        ),
        BottomNavigation(
            title = "Wallet",
            selectedIcon = painterResource(id = R.drawable.account_balance_walletfilled_24px),
            unselectedIcon = painterResource(id = R.drawable.account_balance_wallet_24px),
            hasNews = false,
            onClick = {
                coroutineScope.launch {
                    navController.navigate(Routes.walletScreen)
                }
            }
        ),
        BottomNavigation(
            //notification
            title = "Bills",
            selectedIcon = painterResource(id = R.drawable.billsfilled_24px),
            unselectedIcon = painterResource(id = R.drawable.bills_24px),
            hasNews = true,
            onClick = {
                coroutineScope.launch {
                    navController.navigate(Routes.billsScreen)
                }
            }
        ),
        BottomNavigation(
            title = "Budget",
            selectedIcon = painterResource(id = R.drawable.budgetfilled_24px),
            unselectedIcon = painterResource(id = R.drawable.budget_24px),
            hasNews = false,
            onClick = {
                coroutineScope.launch {
                    navController.navigate(Routes.budgetScreen)
                }
            }
        )
    )
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                //navController.navigate(item.title)
                                item.onClick()
                            },
                            label = { Text(text = item.title) },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge { Text(text = item.badgeCount.toString()) }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize())
        }
    }
}

@Preview
@Composable
fun NavigationPreview(){
    BottomNavigationBar(navController = NavController(LocalContext.current))
}