package com.example.smartspendv6.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smartspendv6.R
import com.example.smartspendv6.components.GradientButton
import com.example.smartspendv6.data.Card
import com.example.smartspendv6.dialog.AddCardPromptDialog
import com.example.smartspendv6.dialog.CardInfoDialog
import com.example.smartspendv6.routes.Routes
import com.example.smartspendv6.routes.Routes.signup
import com.example.smartspendv6.sections.getGradient
import com.example.smartspendv6.ui.theme.Smartspendv6Theme
import com.example.smartspendv6.viewmodel.CardViewModel
import com.example.smartspendv6.viewmodel.UserViewModel

@Composable
fun Login_Screen(
    navController: NavController,
    userViewModel: UserViewModel= viewModel(),
    cardViewModel: CardViewModel = viewModel()
){
    var userCards by remember { mutableStateOf(listOf<Card>()) }
    var showAddCardPrompt by remember { mutableStateOf(false) }
    var showCardInfoDialog by remember { mutableStateOf(false) }
    var shouldNavigateToNextScreen by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = -185.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.username),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var loginUsername by remember { mutableStateOf("") }
        OutlinedTextField(
            value = loginUsername,
            onValueChange = {loginUsername=it},
            label = { Text(stringResource(R.string.username)) }
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.password),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var loginPassword by remember { mutableStateOf("") }
        OutlinedTextField(
            value = loginPassword,
            onValueChange = {loginPassword=it},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "Don't have an account? Sign Up",
            Modifier.clickable {
                navController.navigate(signup)
            }
        )

        //Login Button
        Column(
            modifier = Modifier.offset(x = 10.dp, y = 350.dp)
        ) {
            val scope = rememberCoroutineScope()
            val gradient = Brush.linearGradient(listOf(Color(0xFF4164BF), Color(0xFF85E299)))
            GradientButton(
                text = "Login",
                gradient = gradient,
                modifier = Modifier
                    .size(width = 200.dp, height = 50.dp),
                onClick = {
                    if (loginUsername.isEmpty() || loginPassword.isEmpty()){
                        Toast.makeText(context,"Please fill in all fields", Toast.LENGTH_SHORT).show()
                    } else {
                        userViewModel.loginUser(loginUsername,loginPassword,onSuccess = {
                            showAddCardPrompt = true
                        }, onError = {
                            Toast.makeText(context,"Error logging in", Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            )
        }
        //For the dialog boxes
        if (showAddCardPrompt){
            AddCardPromptDialog(
                onConfirm = {
                    showAddCardPrompt = false
                    showCardInfoDialog = true
                },
                navController = navController,
                onDismiss = {
                    showAddCardPrompt = false
                    if(shouldNavigateToNextScreen){
                        navigateToNextScreen(navController,userCards)
                    }
                }
            )
        }
        if (showCardInfoDialog){
            CardInfoDialog(
                navController = navController,
                onAddNewCard = {
                        newCard ->
                    cardViewModel.addTheCards(
                        cardType = newCard.cardType,
                        cardNumber = newCard.cardNumber,
                        cardYear = newCard.cardYear,
                        cardCVV = newCard.cvv,
                        balance = newCard.balance
                    )
                },
                onDismiss = { showCardInfoDialog=false
                            if (shouldNavigateToNextScreen){
                                navigateToNextScreen(navController,userCards)
                            }},
                onSave = {cardType, cardNumber, cardYear, cardCvv, balance ->
                    val newCard = Card(
                        cardType=cardType,
                        cardNumber=cardNumber,
                        cardYear=cardYear,
                        cvv = cardCvv,
                        balance = balance,
                        color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
                    )
                    userCards = userCards + newCard
                    showCardInfoDialog = false
                    Toast.makeText(context,"Card Saved", Toast.LENGTH_SHORT).show()
                    if(shouldNavigateToNextScreen){
                        navigateToNextScreen(navController,userCards)
                    }
                }
            )
        }
    }
}

//fun loginUser(
//    scope: CoroutineScope,
//    context: android.content.Context,
//    username: String,
//    password: String,
//    onSuccess: () -> Unit
//){
//    scope.launch {
//        Network.api.postLogin(LoginUsers(username,password)).enqueue(object:
//            Callback<LoginUsers> {
//            override fun onResponse(call: Call<LoginUsers>, response: Response<LoginUsers>) {
//                if (response.isSuccessful){
//                    Toast.makeText(context,"Login Successful", Toast.LENGTH_SHORT).show()
//                    onSuccess()
//                } else {
//                    Toast.makeText(context,"Error logging in", Toast.LENGTH_SHORT).show()
//                }
//            }
//            override fun onFailure(call: Call<LoginUsers>, t: Throwable) {
//                Log.e("Log in error:","Error while logging in",t)
//                Toast.makeText(context,"Server issues encountered", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}

fun navigateToNextScreen(navController: NavController, userCards: List<Card>){
    navController.navigate(Routes.bottomNav) {
        popUpTo(Routes.login) {
            inclusive = true
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    Smartspendv6Theme {
        //Login_Screen()
    }
}
