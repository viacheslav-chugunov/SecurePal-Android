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
fun HiddenRecordText(
    text: String,
    @StringRes labelRes: Int,
    hiddenTintColor: Color,
    hidden: Boolean,
    onHiddenChanged: (Boolean) -> Unit,
    multiline: Boolean = true
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RecordText(
            text = text,
            labelRes = labelRes,
            passwordMask = hidden,
            multiline = multiline,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
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