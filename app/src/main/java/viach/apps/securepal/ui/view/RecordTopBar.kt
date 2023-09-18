package viach.apps.securepal.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecordTopBar(
    doneContainerColor: Color,
    doneContentColor: Color,
    onNavigateBack: () -> Unit,
    onDone: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable { onNavigateBack() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_two_arrows_back),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.back),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(
            onClick = {
                keyboardController?.hide()
                onDone()
            },
            containerColor = doneContainerColor,
            contentColor = doneContentColor
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_done),
                contentDescription = null
            )
        }
    }
}