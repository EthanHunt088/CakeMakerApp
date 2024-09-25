package com.ebc.allaroundcakemakerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ebc.allaroundcakemakerapp.navigation.NavManager
import com.ebc.allaroundcakemakerapp.ui.theme.AllAroundCakeMakerAppTheme
import com.ebc.allaroundcakemakerapp.viewModels.CakeMakerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cakeMakerViewModel: CakeMakerViewModel by viewModels()
        setContent {
            AllAroundCakeMakerAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(cakeMakerViewModel = cakeMakerViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AllAroundCakeMakerAppTheme {
        Greeting("Android")
    }
}