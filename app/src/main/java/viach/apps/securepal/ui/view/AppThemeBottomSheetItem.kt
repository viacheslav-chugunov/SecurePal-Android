package viach.apps.securepal.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import viach.apps.securepal.R

@Composable
fun AppThemeBottomSheetItem(
    @StringRes titleRes: Int,
    isSelected: Boolean,
    onClick: suspend () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .clickable(enabled = !isSelected) {
                coroutineScope.launch {
                    onClick()
                }
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = titleRes),
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.ic_done),
                contentDescription = stringResource(id = titleRes),
                modifier = Modifier.padding(start = 16.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}