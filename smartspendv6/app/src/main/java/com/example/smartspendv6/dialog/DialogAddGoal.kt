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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.smartspendv6.data.Goals
import com.example.smartspendv6.data.Network
import com.example.smartspendv6.routes.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Prompt to add a Goal
@Composable
fun AddGoalPromptDialog(
    navController: NavController,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Goal") },
        text = { Text("Do you want to add a Goal?") },
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

//Dialog to add a new Goal
@Composable
fun GoalInfoDialog(
    navController: NavController,
    onAddNewGoal: (Goals) -> Unit,
    onDismiss: () -> Unit,
    onSave:(String,String)->Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var money by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "New Goal") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("description") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (name.isNotEmpty() || description.isNotEmpty() || money.isNotEmpty()) {
                    onAddNewGoal(Goals(name,description))
                    onSave(name,description)
                    Toast.makeText(context, "Goal Added", Toast.LENGTH_SHORT).show()
                    addGoal(scope,context,name,description,onSuccess = {})
                } else {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save")
            }
        }
    )
}

fun addGoal(
    scope: CoroutineScope,
    context: android.content.Context,
    name: String,
    description: String,
    onSuccess: () -> Unit
){
    scope.launch {
        Network.api.postGoal(Goals(name,description)).enqueue(object:
            Callback<Goals> {
            override fun onResponse(call: Call<Goals>, response: Response<Goals>) {
                if (response.isSuccessful){
                    Toast.makeText(context,"Goal Added Successfully", Toast.LENGTH_SHORT).show()
                    onSuccess()
                } else {
                    Toast.makeText(context,"Incorrect details", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Goals>, t: Throwable) {
                Log.e("Log in error:","Error while logging in",t)
                Toast.makeText(context,"Server issues encountered", Toast.LENGTH_LONG).show()
            }
        })
    }
}