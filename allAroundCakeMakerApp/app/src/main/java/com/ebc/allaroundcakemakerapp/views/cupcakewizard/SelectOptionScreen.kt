package com.ebc.allaroundcakemakerapp.views.cupcakewizard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ebc.allaroundcakemakerapp.R
import com.ebc.allaroundcakemakerapp.data.DataSource
import com.ebc.allaroundcakemakerapp.enums.CakeMakerAppScreenViews
import com.ebc.allaroundcakemakerapp.viewModels.CakeMakerViewModel

@Composable
fun SelectOptionScreen(navController: NavController, cakeMakerViewModel: CakeMakerViewModel) {

    val options = DataSource.flavors.map { stringResource(it) }

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = cakeMakerViewModel.state.flavor == item,
                        onClick = {
                            cakeMakerViewModel.onValue(item, "flavor")
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = cakeMakerViewModel.state.flavor == item,
                        onClick = {
                            cakeMakerViewModel.onValue(item, "flavor")
                        }
                    )
                    Text(item)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = cakeMakerViewModel.state.extraInstructions,
                onValueChange = {
                    cakeMakerViewModel.onValue(it, "extraInstructions")
                },
                label = { Text(stringResource(R.string.extra_instructions)) },
                maxLines = Int.MAX_VALUE,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .verticalScroll(rememberScrollState())
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(R.string.subtotal_price, cakeMakerViewModel.state.total),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    cakeMakerViewModel.reset()
                    navController.popBackStack(CakeMakerAppScreenViews.Start.name, false)
                }
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = cakeMakerViewModel.state.flavor.isNotEmpty(),
                onClick = {
                    navController.navigate(CakeMakerAppScreenViews.Pickup.name)
                }
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }

}