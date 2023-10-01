package viach.apps.securepal.ui.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    onClickAddAuth: () -> Unit,
    onClickAddNote: () -> Unit,
    onClickAddCard: () -> Unit
) {
    if (!show) return

    val stateSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = stateSheet
    ) {
        MainBottomSheetItem(
            titleRes = R.string.add_auth_record,
            descriptionRes = R.string.add_auth_record_description,
            onClick = {
                stateSheet.hide()
                onDismiss()
                onClickAddAuth()
            }
        )
        MainBottomSheetItem(
            titleRes = R.string.add_note,
            descriptionRes = R.string.add_note_description,
            onClick = {
                stateSheet.hide()
                onDismiss()
                onClickAddNote()
            }
        )
        MainBottomSheetItem(
            titleRes = R.string.add_card,
            descriptionRes = R.string.add_card_description,
            onClick = {
                stateSheet.hide()
                onDismiss()
                onClickAddCard()
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}