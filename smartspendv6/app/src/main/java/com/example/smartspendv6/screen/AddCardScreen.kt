package com.example.smartspendv6.screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartspendv6.R
import com.example.smartspendv6.components.GradientButton
import com.example.smartspendv6.routes.Routes
import com.example.smartspendv6.ui.theme.Smartspendv6Theme

@Composable
fun AddCard_Screen(
    navController: NavController
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var mExpanded by remember { mutableStateOf(false) }
        val mCardTypes = listOf("Visa", "Mastercard")
        var mSelectedText by remember { mutableStateOf("") }
        var mTextFieldSize by remember {
            mutableStateOf(Size.Zero)
        }

        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.cardType),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var cardType by remember { mutableStateOf("") }
        OutlinedTextField(
            value = cardType,
            onValueChange = {cardType=it},
            modifier = Modifier
                .onGloballyPositioned { coordinates -> mTextFieldSize = coordinates.size.toSize() },
            label = { Text(stringResource(R.string.cardType)) },
            trailingIcon = {
                Icon(icon, "contentDescription",Modifier.clickable { mExpanded = !mExpanded } )
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { false },
            modifier = Modifier
                .width(with(LocalDensity.current) {
                    mTextFieldSize.width.toDp()
                })
        ) {
            mCardTypes.forEach { label ->
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.cardNumber),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var cardNumber by remember { mutableStateOf("") }
        OutlinedTextField(
            value = cardNumber,
            onValueChange = {cardNumber=it},
            label = { Text(stringResource(R.string.cardNumber)) }
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.cardYear),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var cardYear by remember { mutableStateOf("") }
        OutlinedTextField(
            value = cardYear,
            onValueChange = {cardYear=it},
            label = { Text(stringResource(R.string.cardYear)) }
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.cardCVV),
                modifier = Modifier.offset(x=55.dp)
            )
        }
        var cardCVV by remember { mutableStateOf("") }
        OutlinedTextField(
            value = cardCVV,
            onValueChange = {cardCVV=it},
            label = { Text(stringResource(R.string.cardCVV)) }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "Already have a card? Go to home page",
            Modifier.clickable {
                navController.navigate(Routes.home)
            }
        )
        Spacer(modifier = Modifier.padding(10.dp))
    }
    Column(
        modifier = Modifier.offset(x = 100.dp, y = 650.dp)
    ) {
        val gradient = Brush.linearGradient(listOf(Color(0xFF4164BF), Color(0xFF85E299)))
        GradientButton(
            text = "Add Card",
            gradient = gradient,
            modifier = Modifier
                .size(width = 200.dp, height = 50.dp),
            onClick = { /*navController.navigate(Routes.home)*/ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddCard_ScreenPreview() {
    Smartspendv6Theme {
        AddCard_Screen(navController = rememberNavController())
    }
}