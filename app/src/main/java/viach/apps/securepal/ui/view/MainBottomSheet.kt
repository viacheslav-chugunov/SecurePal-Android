package viach.apps.securepal.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
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

    val stateSheet = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = stateSheet
    ) {
        Text(
            text = stringResource(id = R.string.add_auth_record),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        stateSheet.hide()
                        onDismiss()
                        onClickAddAuth()
                    }
                }
                .padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.add_note),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        stateSheet.hide()
                        onDismiss()
                        onClickAddNote()
                    }
                }
                .padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.add_card),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        stateSheet.hide()
                        onDismiss()
                        onClickAddCard()
                    }
                }
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}