package com.example.smartspendv6.dialog

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smartspendv6.data.Card
import com.example.smartspendv6.data.Network
import com.example.smartspendv6.routes.Routes
import com.example.smartspendv6.sections.getGradient
import com.example.smartspendv6.viewmodel.CardViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Prompt to add a card
@Composable
fun AddCardPromptDialog(
    navController: NavController,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Card")},
        text = {Text("Do you want to add a card?")},
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
                navController.navigate(Routes.home) {
                    popUpTo(Routes.login) { inclusive = true }
                }
            }) {
                Text("No")
            }
        }
    )
}

//Dialog to add a card
@Composable
fun CardInfoDialog(
    navController: NavController,
    onAddNewCard: (Card) -> Unit,
    onDismiss: () -> Unit,
    onSave:(String,String,String,String,Double)->Unit,
    cardViewModel: CardViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var cardType by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var cardYear by remember { mutableStateOf("") }
    var cardCVV by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf(0.0) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Enter Card Information")},
        text = {
            Column {
                OutlinedTextField(
                    value = cardType,
                    onValueChange = { cardType = it },
                    label = { Text("Card Type") }
                )
                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text("Card Number") }
                )
                OutlinedTextField(
                    value = cardYear,
                    onValueChange = { cardYear = it },
                    label = { Text("Card Year") }
                )
                OutlinedTextField(
                    value = cardCVV,
                    onValueChange = { cardCVV = it },
                    label = { Text("CVV") }
                )
                OutlinedTextField(
                    value = balance.toString(),
                    onValueChange = { balance = it.toDouble() },
                    label = { Text("Balance") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (cardType.isNotEmpty() || cardNumber.isNotEmpty() || cardYear.isNotEmpty() || cardCVV.isNotEmpty() || balance.toString().isNotEmpty()) {
//                    onAddNewCard(Card(
//                        cardType = cardType,
//                        cardNumber = cardNumber,
//                        cardYear = cardYear,
//                        cvv = cardCVV,
//                        color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
//                    ))
                    val newCard = Card(
                        cardType = cardType,
                        cardNumber = cardNumber,
                        cardYear = cardYear,
                        cvv = cardCVV,
                        balance = balance,
                        color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
                    )
                    cardViewModel.addTheCards(cardType,cardNumber,cardYear,cardCVV,balance)
                    onAddNewCard(newCard)
                    onSave(cardType,cardNumber,cardYear,cardCVV,balance)
                    Toast.makeText(context, "Card Added", Toast.LENGTH_SHORT).show()
                    onDismiss()
                    addCard(scope,context,cardType,cardNumber,cardYear,cardCVV,balance,onSuccess = {})
                    //navigate to home
                    navController.navigate(Routes.home) {
                        popUpTo(Routes.login) { inclusive = true }
                    }
                } else {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

fun addCard(
    scope: CoroutineScope,
    context: android.content.Context,
    cardType: String,
    cardNumber: String,
    cardYear: String,
    cardCVV: String,
    balance: Double,
    onSuccess: () -> Unit
){
    scope.launch {
        Network.api.postCard(Card(cardType,cardNumber,cardYear,balance,cardCVV,getGradient(Color(0xFF4164BF), Color(0xFF85E299) ))).enqueue(object:
            Callback<Card> {
            override fun onResponse(call: Call<Card>, response: Response<Card>) {
                if (response.isSuccessful){
                    Toast.makeText(context,"Card Added Successfully", Toast.LENGTH_SHORT).show()
                    onSuccess()
                } else {
                    Toast.makeText(context,"Incorrect details", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Card>, t: Throwable) {
                Log.e("Log in error:","Error while logging in",t)
                Toast.makeText(context,"Server issues encountered", Toast.LENGTH_LONG).show()
            }
        })
    }
}