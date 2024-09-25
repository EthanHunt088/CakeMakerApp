package com.ebc.allaroundcakemakerapp.views.onboarding


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ebc.allaroundcakemakerapp.R
import com.ebc.allaroundcakemakerapp.data.PageData
import com.ebc.allaroundcakemakerapp.dataStore.AppBoardingDataStore
import com.ebc.allaroundcakemakerapp.enums.CakeMakerAppScreenViews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainOnBoarding(navController: NavController, appBoardingDataStore: AppBoardingDataStore) {

    val items = ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.page1,
            "¡Hola!",
            "Que alegría verte por aquí, en All Around Cake Maker, la mejor pastelería de la ciudad."
        )
    )

    items.add(
        PageData(
            R.raw.page2,
            "¡Bienvenido!",
            "Aquí encontrarás los mejores cupcakes de todo el mundo mundial, ¡y mucho más!."
        )
    )

    items.add(
        PageData(
            R.raw.page3,
            "¡A cocinar!",
            "¡Manos a la obra! Descubre los cupcakes más deliciosos y sorprende a tus seres queridos."
        )
    )

    val pagerState = rememberPagerState(
        pageCount = { items.size },
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )

    /*val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        scope.launch { }
    }*/

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(state = pagerState) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(items[it].image)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 60.dp)
                        .padding(top = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                    Text(
                        text = items[it].title,
                        modifier = Modifier.padding(top = 50.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = items[it].desc,
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )

                }

            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(top = 60.dp)
            ) {
                repeat(items.size) {
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .height(10.dp)
                            .width(25.dp) // 10 circulo
                            .clip(CircleShape)
                            .background(if (it == pagerState.currentPage) Color.Red else Color.Gray)
                    )
                }
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Row(
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .fillMaxWidth(),
                horizontalArrangement = if (pagerState.currentPage == items.size - 1) Arrangement.Center else Arrangement.SpaceBetween
            ) {
                if (pagerState.currentPage == items.size - 1) {
                    Button(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            //appBoardingDataStore.saveBoarding(true)
                            appBoardingDataStore.saveBoarding(false)
                        }
                        //navController.navigate("Home") {
                        navController.navigate(CakeMakerAppScreenViews.Start.name) {
                            popUpTo(0)
                        }
                    }) {
                        //on mouse over, change the text color to red


                        Text(text = "Entrar", modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .align(Alignment.CenterVertically),
                        )
                    }
                }
            }
        }
    }
}