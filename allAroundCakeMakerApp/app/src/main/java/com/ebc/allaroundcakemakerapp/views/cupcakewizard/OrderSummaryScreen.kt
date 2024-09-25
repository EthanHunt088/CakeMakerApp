package com.ebc.allaroundcakemakerapp.views.cupcakewizard

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ebc.allaroundcakemakerapp.R
import com.ebc.allaroundcakemakerapp.enums.CakeMakerAppScreenViews
import com.ebc.allaroundcakemakerapp.viewModels.CakeMakerViewModel

@Composable
fun OrderSummaryScreen(navController: NavController, cakeMakerViewModel: CakeMakerViewModel) {
    val context = LocalContext.current
    val resources = context.resources

    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        cakeMakerViewModel.state.quantity,
        cakeMakerViewModel.state.quantity
    )

    val orderSummary = stringResource(
        R.string.order_details,
        numberOfCupcakes,
        cakeMakerViewModel.state.flavor,
        cakeMakerViewModel.state.pickupDate,
        cakeMakerViewModel.state.quantity,
        cakeMakerViewModel.state.extraInstructions,
        cakeMakerViewModel.state.pickupInstructions
    )
    val newOrder = stringResource(R.string.new_cupcake_order)

    val items = listOf(
        // Summary line 1: display selected quantity
        Pair(stringResource(R.string.quantity), numberOfCupcakes),
        // Summary line 2: display selected flavor
        Pair(stringResource(R.string.flavor), cakeMakerViewModel.state.flavor),
        // Summary line 3: display selected pickup date
        Pair(stringResource(R.string.pickup_date), cakeMakerViewModel.state.pickupDate),
        Pair(stringResource(R.string.extra_instructions), cakeMakerViewModel.state.extraInstructions),
        Pair(stringResource(R.string.pickup_instructions), cakeMakerViewModel.state.pickupInstructions)
    )

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach {
                Text(it.first.uppercase())
                Text(text = it.second, fontWeight = FontWeight.Bold)
                Divider(thickness = 1.dp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.subtotal_price, cakeMakerViewModel.state.total),
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { shareOrder(context, newOrder, orderSummary) }
                ) {
                    Text(stringResource(R.string.send))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        //cakeMakerViewModel.reset()
                        //navController.popBackStack(CakeMakerAppScreenViews.Start.name, inclusive = false)
                        navController.navigate(CakeMakerAppScreenViews.Finish.name)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Text(stringResource(R.string.end))
                }
            }
        }
    }
}

private fun shareOrder(context: Context, subject: String, summary: String) {

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )
}