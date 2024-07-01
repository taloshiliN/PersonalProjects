package com.example.smartspendv6.screen

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.smartspendv6.data.Network
import com.example.smartspendv6.data.SignupUsers
import com.example.smartspendv6.ui.theme.Smartspendv6Theme
import com.example.smartspendv6.viewmodel.UserViewModel
//import com.example.smartspendv6.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnrememberedMutableState", "MutableCollectionMutableState")
@Composable
fun Signup_Screen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
){

    var userData by remember {
        mutableStateOf(emptyList<SignupUsers>())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isVisible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.username),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var username by remember { mutableStateOf("") }
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text(stringResource(R.string.username)) }
        )
        Spacer(modifier = Modifier.padding(10.dp))

        //Email
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.email),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.padding(10.dp))


        //Password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.password),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var password by remember { mutableStateOf("") }
        OutlinedTextField(
            value = password,
            onValueChange = {password=it},
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(10.dp))


        //Re-enter Password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.ReEnterPassword),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var verifyPassword by remember { mutableStateOf("") }
        OutlinedTextField(
            value = verifyPassword,
            onValueChange = {verifyPassword=it},
            label = { Text(text = stringResource(R.string.ReEnterPassword)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(5.dp))



        Text(
            text = "Already Have an Account? Login",
            modifier = Modifier.clickable {
                navController.navigate("Login_Screen")
            }
        )
        Spacer(modifier = Modifier.padding(50.dp))

        //Signup Button
        Column(
            Modifier.offset(x = 1.dp,y = 10.dp)
        ) {
            val scope = rememberCoroutineScope()
            val context = LocalContext.current
            val gradient = Brush.linearGradient(listOf(Color(0xFF4164BF), Color(0xFF85E299)))
            GradientButton(
                text = "Sign Up",
                gradient = gradient,
                modifier = Modifier
                    .size(width =200.dp, height = 50.dp),
                onClick = {
                    //Check if fields are empty
                    if(username.isEmpty() || email.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()){
                        Toast.makeText(context,"Please fill in all the fields", Toast.LENGTH_LONG).show()
                    } else if (password != verifyPassword){
                        Toast.makeText(context,"Password does not match",Toast.LENGTH_SHORT).show()
                    } else {
                        userViewModel.signUpUser(username,email,password, onSuccess = {
                            Toast.makeText(context,"Account Created",Toast.LENGTH_SHORT).show()
                            navController.navigate("Login_Screen")
                        }, onError = {
                            Toast.makeText(context,"Error creating account",Toast.LENGTH_SHORT).show()
                        })
//                        scope.launch {
//                            isLoading = true
//                            try {
//                                withContext(Dispatchers.Main){
//                                    userData = Network.api.postUsers().await()
//                                }
//                                isVisible = !isVisible
//                            } catch (e: Exception){
//                                Log.e("Post error:","Error posting data",e)
//                                Toast.makeText(context,"Error posting data",Toast.LENGTH_SHORT).show()
//                            } finally {
//                                isLoading= false
//                            }
//                        }
                    }
                },
            )
        }
    }
}

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
//                    Toast.makeText(context,"Account Created",Toast.LENGTH_SHORT).show()
//                    navController.navigate("Login_Screen")
//                } else {
//                    Toast.makeText(context,"Error creating account",Toast.LENGTH_SHORT).show()
//                }
//            }
//            override fun onFailure(call: Call<SignupUsers>, t: Throwable) {
//                Log.e("SignUp:","Error Signing Up",t)
//                Toast.makeText(context,"Connect to a network to Sign Up",Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}



@Preview(showBackground = true)
@Composable
fun SignUpPreview(){
    Smartspendv6Theme {
        //Signup_Screen()
    }
}