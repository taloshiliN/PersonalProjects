package com.example.smartspendv6.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartspendv6.BottomNavigationBar
import com.example.smartspendv6.components.GradientButton
import com.example.smartspendv6.data.Budget
import java.time.format.DateTimeFormatter

@Composable
fun Budget_Screen(navController: NavController) {
    BottomNavigationBar(navController = navController)
    val (budgets, setBudgets) = remember { mutableStateOf(listOf<Budget>()) }
    val (nameOfBudget, setNameOfBudget) = remember { mutableStateOf("") }
    val (amountOfBudget, setAmountBudget) = remember { mutableStateOf("") }
    val (nameError, setNameError) = remember { mutableStateOf("") }
    val (amountError, setAmountError) = remember { mutableStateOf("") }
    val context = LocalContext.current

    Spacer(modifier = Modifier.height(16.dp))
    //Main_menu()

    fun addBudget() {
        var hasError = false

        if (nameOfBudget.isBlank()) {
            setNameError("Please enter budget name")
            hasError = true
        } else {
            setNameError("")
        }

        val amount = amountOfBudget.toDoubleOrNull()
        if (amount == null) {
            setAmountError("Please enter a budget amount")
            hasError = true
        } else {
            setAmountError("")
        }

        if (!hasError) {
            val newBudget = amount?.let { Budget(nameOfBudget, it) }
            setBudgets((budgets + newBudget) as List<Budget>)

            // Clear input fields after adding the budget
            setNameOfBudget("")
            setAmountBudget("")
        }
    }

    fun deleteBudget(budget: Budget) {
        setBudgets(budgets - budget)

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Budget",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(60.dp))

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            OutlinedTextField(
                value = nameOfBudget,
                onValueChange = { setNameOfBudget(it) },
                label = { Text(text = "Budget name") },
                isError = nameError.isNotBlank()
            )
            if (nameError.isNotBlank()) {
                Text(nameError, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = amountOfBudget,
                onValueChange = { setAmountBudget(it) },
                label = { Text(text = "Budget amount") },
                isError = amountError.isNotBlank(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (amountError.isNotBlank()) {
                Text(amountError, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(10.dp))

            val gradient = Brush.linearGradient(listOf(Color(0xFF4164BF), Color(0xFF85E299)))

            GradientButton(
                onClick = {

                    addBudget() },
                text = "Add new budget",
                gradient = gradient,
                modifier = Modifier.size(width = 120.dp, height = 40.dp)
            )


            Spacer(modifier = Modifier.height(10.dp))

            //HorizontalDivider()

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "My Budgets",
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(budgets) { budget ->
                BudgetRow(
                    budget = budget,
                    onDeleteClicked = { deleteBudget(budget) }
                )
            }
        }
    }
}

@Composable
fun BudgetRow(
    modifier: Modifier = Modifier,
    budget: Budget,
    onDeleteClicked: (Budget) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(5.dp)
            .clip(
                RoundedCornerShape(33.dp)
            )
            .fillMaxWidth(),
        color = Color(0xFF85E299)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 6.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = budget.budgetName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "N$${budget.budgetAmount}",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(text = budget.entryDate.format(DateTimeFormatter.ofPattern("EEE, d, MMM")),
                    style = MaterialTheme.typography.titleSmall)
            }
            IconButton(onClick = { onDeleteClicked(budget) }) {
                Icon(Icons.Default.Delete, contentDescription = "delete")
            }
        }
    }
}