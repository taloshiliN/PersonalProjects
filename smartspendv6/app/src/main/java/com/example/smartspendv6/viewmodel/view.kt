package com.example.smartspendv6.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartspendv6.data.Card
import com.example.smartspendv6.data.LoginUsers
import com.example.smartspendv6.data.Network
import com.example.smartspendv6.data.SignupUsers
import com.example.smartspendv6.sections.getGradient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel(){
    var userData by mutableStateOf<SignupUsers?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun signUpUser(username: String, email: String, password: String, onSuccess: ()->Unit, onError: (String)->Unit) {
        viewModelScope.launch {
            isLoading = true
            Network.api.postUsers(SignupUsers(username, email, password)).enqueue(object :
                Callback<SignupUsers> {
                override fun onResponse(call: Call<SignupUsers>, response: Response<SignupUsers>) {
                    isLoading = false
                    if (response.isSuccessful) {
                        userData = response.body()
                        onSuccess()
                    } else {
                        onError("Error creating account")
                    }
                }

                override fun onFailure(call: Call<SignupUsers>, t: Throwable) {
                    isLoading = false
                    onError("Connect to a network to Sign Up")
                }
            })
        }
    }
    fun loginUser(username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            Network.api.postLogin(LoginUsers(username, password)).enqueue(object : Callback<LoginUsers> {
                override fun onResponse(call: Call<LoginUsers>, response: Response<LoginUsers>) {
                    isLoading = false
                    if (response.isSuccessful) {
                        onSuccess()
                    } else {
                        onError("Error logging in")
                    }
                }

                override fun onFailure(call: Call<LoginUsers>, t: Throwable) {
                    isLoading = false
                    onError("Server issues encountered")
                }
            })
        }
    }
//    fun addCard(card: Card) {
//        cardData = cardData + card
//    }
}
//
//fun signUpUser(
//    scope: CoroutineScope,
//    context: android.content.Context,
//    username: String,
//    email: String,
//    password: String,
//    navController: NavController
//){
//    scope.launch {
//        Network.api.postUsers(SignupUsers(username,email,password)).enqueue(object: Callback<SignupUsers>{
//            override fun onResponse(call: Call<SignupUsers>, response: Response<SignupUsers>) {
//                if (response.isSuccessful){
//                    Toast.makeText(context,"Account Created", Toast.LENGTH_SHORT).show()
//                    navController.navigate("Login_Screen")
//                } else {
//                    Toast.makeText(context,"Error creating account", Toast.LENGTH_SHORT).show()
//                }
//            }
//            override fun onFailure(call: Call<SignupUsers>, t: Throwable) {
//                Log.e("SignUp:","Error Signing Up",t)
//                Toast.makeText(context,"Connect to a network to Sign Up", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}

class CardViewModel: ViewModel(){
    private val _userCards = MutableStateFlow<List<Card>>(emptyList())
    val userCards: StateFlow<List<Card>> = _userCards

    fun addTheCards(cardType: String, cardNumber: String, cardYear: String, cardCVV: String,balance: Double) {
        val newCard = Card(
            cardType = cardType,
            cardNumber = cardNumber,
            cardYear = cardYear,
            cvv = cardCVV,
            balance = balance,
            color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
        )
        _userCards.update { currentCards -> currentCards + newCard }
    }
}