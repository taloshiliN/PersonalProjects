package com.example.smartspendv6.screen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartspendv6.BottomNavigationBar
import com.example.smartspendv6.data.Card
import com.example.smartspendv6.data.Goals
import com.example.smartspendv6.sections.AddGoalSection
import com.example.smartspendv6.sections.CardsSection
import com.example.smartspendv6.sections.CircularProgressBar
import com.example.smartspendv6.sections.MyGoalsSection
import com.example.smartspendv6.sections.getGradient
import com.example.smartspendv6.ui.theme.Smartspendv6Theme

@Composable
fun Home_Screen(
    navController: NavController,
    onAddNewGoal: (Goals) -> Unit,
    onDismiss: () -> Unit,
    onSave:(String,String)->Unit,
    userCards: List<Card>
) {
    //Display bottom navigation bar on home screen
    BottomNavigationBar(navController = navController)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        AddGoalSection(navController, onAddNewGoal, onDismiss, onSave)
        CircularProgressBar(percentage = 0.9f, number = 100)
        Spacer(modifier = Modifier.padding(16.dp))
        CardsSection(userCards = userCards, onRemoveCard = {}, onAddCard = {})
        Spacer(modifier = Modifier.height(16.dp))
        MyGoalsSection(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    val userCards = listOf(
        Card(
            cardType = "",
            cardNumber = "",
            cardYear = "",
            cvv = "",
            balance = 0.0,
            color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
        ),
    )
    Smartspendv6Theme {
        Home_Screen(navController = navController, onAddNewGoal = {}, onDismiss = {}, onSave = {_,_->},userCards = userCards)
    }
}