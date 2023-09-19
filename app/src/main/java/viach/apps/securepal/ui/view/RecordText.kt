package viach.apps.securepal.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun RecordText(
    text: String,
    @StringRes labelRes: Int,
    modifier: Modifier = Modifier,
    passwordMask: Boolean = false,
    multiline: Boolean = true
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = CutCornerShape(8.dp)
            ).padding(all = 16.dp)
    ) {
        Text(
            text = stringResource(id = labelRes),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = if (passwordMask) "••••••••" else text,
            modifier = Modifier.fillMaxWidth(),
            maxLines = if (multiline) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}