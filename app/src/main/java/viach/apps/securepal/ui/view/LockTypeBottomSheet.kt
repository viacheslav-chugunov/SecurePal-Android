package viach.apps.securepal.ui.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.storage.model.Lock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LockTypeBottomSheet(
    show: Boolean,
    selectedType: Lock.Type,
    onDismiss: () -> Unit,
    onLockTypeSelected: (Lock.Type) -> Unit
) {
    if (!show) return

    val stateSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = stateSheet
    ) {
        Text(
            text = stringResource(id = R.string.select_lock_type),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        BottomSheetItem(
            titleRes = R.string.disabled,
            isSelected = selectedType == Lock.Type.EMPTY,
            onClick = {
                stateSheet.hide()
                onDismiss()
                onLockTypeSelected(Lock.Type.EMPTY)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        BottomSheetItem(
            titleRes = R.string.pin,
            isSelected = selectedType == Lock.Type.PIN,
            onClick = {
                stateSheet.hide()
                onDismiss()
                onLockTypeSelected(Lock.Type.PIN)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        BottomSheetItem(
            titleRes = R.string.password,
            isSelected = selectedType == Lock.Type.PASSWORD,
            onClick = {
                stateSheet.hide()
                onDismiss()
                onLockTypeSelected(Lock.Type.PASSWORD)
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}