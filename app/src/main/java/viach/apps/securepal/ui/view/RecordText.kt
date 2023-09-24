package viach.apps.securepal.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordText(
    text: String,
    @StringRes labelRes: Int,
    modifier: Modifier = Modifier,
    onLongClick: (() -> Unit)? = null,
    passwordMask: Boolean = false,
    multiline: Boolean = true,
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = CutCornerShape(8.dp)
            )
            .clip(CutCornerShape(8.dp))
            .combinedClickable(enabled = onLongClick != null, onLongClick = onLongClick, onClick = {})
            .padding(all = 16.dp)
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