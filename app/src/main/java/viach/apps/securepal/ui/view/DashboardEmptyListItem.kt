package viach.apps.securepal.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

fun LazyListScope.empty(
    items: List<Any>,
    @StringRes messageRes: Int,
    messageColor: Color,
    @StringRes buttonRes: Int,
    buttonColors: ButtonColors,
    onButtonClick: () -> Unit
) {
    if (items.isEmpty()) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = messageRes),
                    modifier = Modifier.padding(all = 16.dp),
                    textAlign = TextAlign.Center,
                    color = messageColor
                )
                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.8f)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    colors = buttonColors
                ) {
                    Text(text = stringResource(id = buttonRes))
                }
            }
        }
    }
}