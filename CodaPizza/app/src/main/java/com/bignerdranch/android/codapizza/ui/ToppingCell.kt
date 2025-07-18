package com.bignerdranch.android.codapizza.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bignerdranch.android.codapizza.model.Topping
import com.bignerdranch.android.codapizza.model.ToppingPlacement

// create a dedicated composable function for previewing only
@Preview
@Composable
// note that the visibility modifier must be private
private fun ToppingCellPreview() {
    // set default values for composables
    ToppingCell(
        topping = Topping.Pepperoni,
        placement = ToppingPlacement.Left,
        onClickTopping = {}
    )
}
// create another preview composable function to see another version of the UI
@Preview
@Composable
private fun ToppingCellPreviewNotOnPizza() {
    ToppingCell(
        topping = Topping.Pepperoni,
        placement = null,
        onClickTopping = {}
    )
}

@Composable
fun ToppingCell(
    topping: Topping,
    placement: ToppingPlacement?,
    modifier: Modifier = Modifier,
    onClickTopping: () -> Unit
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClickTopping }
            .padding(vertical = 4.dp, horizontal = 16. dp)
            ){
        Checkbox(
            checked = (placement != null),
            onCheckedChange = { /* TODO */ }
        )
        Column (
            modifier = modifier
                .weight(1f, fill=true)
                .padding(start=4.dp)
                ) {
            Text(
                // stringResource() is used to fetch strings
                text = stringResource(id = topping.toppingName),
                style= MaterialTheme.typography.body1
            )
            if (placement != null) {
                Text(
                    text = stringResource(id = placement.label),
                    style= MaterialTheme.typography.body2
                )
            }
        }
    }
}
