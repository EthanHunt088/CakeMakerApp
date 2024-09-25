package com.ebc.allaroundcakemakerapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ebc.allaroundcakemakerapp.R
import com.ebc.allaroundcakemakerapp.dataStore.AppBoardingDataStore
import com.ebc.allaroundcakemakerapp.enums.CakeMakerAppScreenViews
import com.ebc.allaroundcakemakerapp.viewModels.CakeMakerViewModel
import com.ebc.allaroundcakemakerapp.views.cupcakewizard.FinishScreen
import com.ebc.allaroundcakemakerapp.views.cupcakewizard.OrderSummaryScreen
import com.ebc.allaroundcakemakerapp.views.cupcakewizard.SelectDateScreen
import com.ebc.allaroundcakemakerapp.views.cupcakewizard.SelectOptionScreen
import com.ebc.allaroundcakemakerapp.views.cupcakewizard.StartOrderScreen
import com.ebc.allaroundcakemakerapp.views.onboarding.MainOnBoarding
import com.ebc.allaroundcakemakerapp.views.onboarding.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar(
    currentScreen: CakeMakerAppScreenViews,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
    )
}

@Composable
fun NavManager(cakeMakerViewModel: CakeMakerViewModel) {
    val context = LocalContext.current
    val dataStore = AppBoardingDataStore(context)
    val store = dataStore.getBoarding.collectAsState(initial = false)

    val navController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = CakeMakerAppScreenViews.valueOf(
        backStackEntry?.destination?.route ?: CakeMakerAppScreenViews.Start.name
    )

    Scaffold(
        topBar = {
            if(currentScreen != CakeMakerAppScreenViews.Splash &&
                currentScreen != CakeMakerAppScreenViews.OnBoarding &&
                currentScreen != CakeMakerAppScreenViews.Finish) {
                CupcakeAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = if (store.value) "Home" else "Splash",
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            composable(CakeMakerAppScreenViews.OnBoarding.name) {
                MainOnBoarding(navController = navController, appBoardingDataStore = dataStore)
            }
            composable(CakeMakerAppScreenViews.Home.name) {
                //HomeView(navController = navController, cakeMakerViewModel = cakeMakerViewModel)
                Text(text = "Home View")
            }
            composable(CakeMakerAppScreenViews.Splash.name) {
                SplashScreen(navController = navController, store = store.value)
            }
            composable(CakeMakerAppScreenViews.Start.name) {
                StartOrderScreen(
                    navController = navController,
                    cakeMakerViewModel = cakeMakerViewModel
                )
            }
            composable(CakeMakerAppScreenViews.Flavor.name) {
                SelectOptionScreen(
                    navController = navController,
                    cakeMakerViewModel = cakeMakerViewModel
                )
            }
            composable(CakeMakerAppScreenViews.Pickup.name) {
                SelectDateScreen(
                    navController = navController,
                    cakeMakerViewModel = cakeMakerViewModel
                )
            }
            composable(CakeMakerAppScreenViews.Summary.name) {
                OrderSummaryScreen(
                    navController = navController,
                    cakeMakerViewModel = cakeMakerViewModel
                )
            }
            composable(CakeMakerAppScreenViews.Finish.name) {
                FinishScreen(
                    navController = navController,
                    cakeMakerViewModel = cakeMakerViewModel
                )
            }
        }
    }
}