package viach.apps.securepal.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R

@Composable
fun HiddenRecordTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    @StringRes labelRes: Int,
    hidden: Boolean,
    onHiddenChanged: (Boolean) -> Unit,
    hiddenTintColor: Color,
    multiline: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RecordTextField(
            value = value,
            onValueChanged = onValueChanged,
            labelRes = labelRes,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            passwordMask = hidden,
            multiline = multiline
        )
        IconButton(
            onClick = { onHiddenChanged(!hidden) },
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = if (hidden) R.drawable.ic_hidden else R.drawable.ic_visible),
                contentDescription = null,
                tint = hiddenTintColor
            )
        }
    }
}