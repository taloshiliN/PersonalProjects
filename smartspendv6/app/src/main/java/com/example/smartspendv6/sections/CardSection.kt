package com.example.smartspendv6.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspendv6.R
import com.example.smartspendv6.data.Card
import com.example.smartspendv6.ui.theme.Smartspendv6Theme

//val cards = listOf(
//    Card(
//        cardType = "VISA",
//        cardNumber = "1234 5678 9012 3456",
//        cardYear = "Business",
//        cvv = "701",
//        color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
//    ),
//    Card(
//        cardType = "MASTER CARD",
//        cardNumber = "3244 2303 0989 0096",
//        cardYear = "Savings",
//        cvv = "23.50",
//        color = getGradient(Color(0xFF4164BF),Color(0xFF85E299))
//    ),
//    Card(
//        cardType = "MASTER CARD",
//        cardNumber = "9843 7653 2420 0095",
//        cardYear = "Business",
//        cvv = "2.40",
//        color = getGradient(Color(0xFF4164BF),Color(0xFF85E299))
//    ),
//    Card(
//        cardType = "VISA",
//        cardNumber = "1234 5678 9012 3456",
//        cardYear = "Business",
//        cvv = "701",
//        color = getGradient(Color(0xFF4164BF),Color(0xFF85E299))
//    )
//)

fun getGradient(
    startColor: Color,
    endColor: Color
): Brush{
    return Brush.linearGradient(
        colors = listOf(startColor, endColor)
    )
}
@Composable
fun CardItem(
    index:Int,
    newCards: Card
){

    val card = cards[index]
    var lastItemPadding = 0.dp
    if(index == cards.size - 1){
        lastItemPadding = 16.dp
    }

    var image = painterResource(id = R.drawable.ic_visa)
    if(newCards.cardType == "MASTER CARD"){
        image = painterResource(id = R.drawable.ic_mastercard)
    }

    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = lastItemPadding)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(card.color)
                .width(250.dp)
                .height(160.dp)
                .clickable { }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = image,
                contentDescription = card.cardNumber,
                modifier = Modifier.width(60.dp)
            )
            Text(
                text = card.cardNumber,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
val cards = listOf(
    Card(
        cardType = "VISA",
        cardNumber = "1234 5678 9012 3456",
        cardYear = "Business",
        cvv = "701",
        balance = 200.00,
        color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
    ),
    Card(
        cardType = "MASTER CARD",
        cardNumber = "3244 2303 0989 0096",
        cardYear = "Savings",
        cvv = "23.50",
        balance = 200.00,
        color = getGradient(Color(0xFF4164BF),Color(0xFF85E299))
    ),
    Card(
        cardType = "MASTER CARD",
        cardNumber = "9843 7653 2420 0095",
        cardYear = "Business",
        cvv = "2.40",
        balance = 200.00,
        color = getGradient(Color(0xFF4164BF),Color(0xFF85E299))
    ),
    Card(
        cardType = "VISA",
        cardNumber = "1234 5678 9012 3456",
        cardYear = "Business",
        cvv = "701",
        balance = 200.00,
        color = getGradient(Color(0xFF4164BF),Color(0xFF85E299))
    )
)

@Composable
fun CardsSection(
    userCards: List<Card>,
    onRemoveCard: (Card) -> Unit,
    onAddCard: (Card) -> Unit
){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "My Cards",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow {
            items(userCards){
                    creditCard -> CardRow(cards = creditCard, onCardClick = {
                onRemoveCard(creditCard)
            })
            }
        }
    }
}

@Composable
fun CardRow(
    modifier: Modifier = Modifier,
    cards: Card,
    onCardClick: (Card) -> Unit
){
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(cards.color)
            .width(250.dp)
            .height(160.dp)
            .clickable { }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var lastItemPadding = 16.dp
        var image = painterResource(id = R.drawable.ic_visa)
        if(cards.cardType == "MASTER CARD"){
            image = painterResource(id = R.drawable.ic_mastercard)
        } else if (cards.cardType == "VISA"){
            image = painterResource(id = R.drawable.ic_visa)
        }
        Box(
        modifier = Modifier
            .padding(start = 0.dp, end = lastItemPadding)
    ) {
            Column(
                modifier
                    .clickable { onCardClick(cards) }
                    .padding(
                        horizontal = 14.dp,
                        vertical = 6.dp
                    ),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = image,
                    contentDescription = cards.cardNumber,
                    modifier = Modifier.width(50.dp)
                )
                Text(
                    text = cards.cardNumber,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardsSectionPreview() {
    val sampleCards = listOf(
        Card(
            cardType = "",
            cardNumber = "",
            cardYear = "",
            cvv = "",
            balance = 200.00,
            color = getGradient(Color(0xFF4164BF), Color(0xFF85E299))
        ),
    )

    Smartspendv6Theme {
        CardsSection(userCards = sampleCards, onRemoveCard = {}, onAddCard = {})
    }
}