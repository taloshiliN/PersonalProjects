package com.example.smartspendv6.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartspendv6.data.Card
import com.example.smartspendv6.data.Goals
import com.example.smartspendv6.data.Network
import com.example.smartspendv6.dialog.CardInfoDialog
import com.example.smartspendv6.dialog.GoalInfoDialog
import com.example.smartspendv6.routes.Routes
import com.example.smartspendv6.ui.theme.Smartspendv6Theme

//val goals = listOf(
//    Goals(
//        name = "No spending",
//        description = "No spending"
//    ),
//)


@Composable
fun MyGoalsSection(
    navController: NavController
){
    val goals = remember { mutableStateListOf<Goals>() }
    var showGoalInfoDialog by remember { mutableStateOf(false) }
    var showAddCardPrompt by remember { mutableStateOf(false) }

//    LaunchedEffect(Unit) {
//        val apiService = Network.api
//        try {
//            val fetchedGoals = apiService.getGoal()
//            goals.addAll(fetchedGoals)
//        } catch (e: Exception) {
//            // Handle error
//        }
//    }
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "My Goals",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = { showGoalInfoDialog = true }) {
            Text("Add Goal")
        }
    }
    LazyColumn{
        items(goals.size){ index ->
            GoalItem(goals[index])
        }
    }
    if (showGoalInfoDialog){
        GoalInfoDialog(
            navController = navController,
            onDismiss = { showGoalInfoDialog=false },
            onSave = {name, description ->
                goals.add(Goals(name=name, description=description))
                showGoalInfoDialog = false
            },
            onAddNewGoal = {newGoal ->
                goals.add(newGoal)
                showGoalInfoDialog = false
            }
        )
    }
}

//@Composable
//fun GoalRow(
//    modifier: Modifier = Modifier,
//    goal: Goals
//){
//    Surface(
//        modifier
//            .padding(4.dp)
//            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
//            .fillMaxWidth(),
//        color = Color(0xFF4164BF)
//    ) {
//        Column {
//            modifier
//                .clickable {  }
//                .padding(
//                    horizontal = 14.dp,
//                    vertical = 16.dp
//                ),
//            horizontalAlignment = Alignment.Start
//        }
//    }
//}

@Composable
fun GoalItem(
    //index: Int,
    goal: Goals
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                //end = lastItemPaddingEnd
            )
    ) {
        Column {
            Text(text = goal.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = goal.description, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoalItemPreview() {
    val navController = rememberNavController()
    Smartspendv6Theme {
        MyGoalsSection(navController = navController)
    }
}