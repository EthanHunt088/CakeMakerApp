package com.ebc.allaroundcakemakerapp.views.cupcakewizard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ebc.allaroundcakemakerapp.R
import com.ebc.allaroundcakemakerapp.enums.CakeMakerAppScreenViews
import com.ebc.allaroundcakemakerapp.viewModels.CakeMakerViewModel

@Composable
fun FinishScreen(navController: NavController, cakeMakerViewModel: CakeMakerViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.finish)
            )

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .size(400.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center)
            )
        }
        Text(
            text = stringResource(R.string.Thanks) ,
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                cakeMakerViewModel.reset()
                navController.popBackStack(CakeMakerAppScreenViews.Start.name, inclusive = false)
            }
        ) {
            Text(stringResource(R.string.comback_init))
        }
    }

}
