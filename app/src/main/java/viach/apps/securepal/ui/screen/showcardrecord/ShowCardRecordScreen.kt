package viach.apps.securepal.ui.screen.showcardrecord

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.securepal.Screen
import viach.apps.securepal.ui.screen.showauthrecord.ShowAuthRecordAction
import viach.apps.securepal.ui.view.HiddenRecordText
import viach.apps.securepal.ui.view.RecordText
import viach.apps.securepal.ui.view.ShowRecordTopBar

@Composable
fun ShowCardRecordScreen(
    state: ShowCardRecordState,
    onAction: (ShowCardRecordAction) -> Unit,
    openScreen: (Screen) -> Unit,
    navigateBack: () -> Unit,
    showMessage: (String) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state.closeScreen) {
        if (state.closeScreen) {
            navigateBack()
        }
    }

    LaunchedEffect(state.openScreen) {
        state.openScreen?.let {
            openScreen(it)
            onAction(ShowCardRecordAction.HandleOpenedScreen)
        }
    }

    LaunchedEffect(state.showMessageRes) {
        if (state.showMessageRes != 0) {
            showMessage(context.getString(state.showMessageRes))
            onAction(ShowCardRecordAction.HandleShownMessage)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShowRecordTopBar(
            editContainerColor = MaterialTheme.colorScheme.tertiary,
            editContentColor = MaterialTheme.colorScheme.onTertiary,
            onNavigateBack = navigateBack,
            onEditClick = { onAction(ShowCardRecordAction.OpenScreen(Screen.EditCardRecord(state.cardRecord))) },
            onDeleteClick = { onAction(ShowCardRecordAction.DeleteCardRecord) }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = CutCornerShape(16.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            RecordText(
                text = state.cardRecord.title,
                labelRes = R.string.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onLongClick = { onAction(ShowCardRecordAction.CopyToClipboard(state.cardRecord.title, R.string.title_copied)) }
            )
            if (state.cardRecord.owner.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                RecordText(
                    text = state.cardRecord.owner,
                    labelRes = R.string.owner,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onLongClick = { onAction(ShowCardRecordAction.CopyToClipboard(state.cardRecord.owner, R.string.owner_copied)) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            RecordText(
                text = state.cardRecord.number,
                labelRes = R.string.card_number,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onLongClick = { onAction(ShowCardRecordAction.CopyToClipboard(state.cardRecord.number, R.string.card_number_copied)) }
            )
            if (state.cardRecord.expiration.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                RecordText(
                    text = state.cardRecord.expiration,
                    labelRes = R.string.expiration_date,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onLongClick = { onAction(ShowCardRecordAction.CopyToClipboard(state.cardRecord.expiration, R.string.expiration_date_copied)) }
                )
            }
            if (state.cardRecord.check.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                HiddenRecordText(
                    text = state.cardRecord.check,
                    labelRes = R.string.verification_number,
                    onLongClick = { onAction(ShowCardRecordAction.CopyToClipboard(state.cardRecord.check, R.string.verification_number_copied)) },
                    hidden = !state.showCheck,
                    onHiddenChanged = { onAction(ShowCardRecordAction.ShowCheck(!it)) },
                    hiddenTintColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
            if (state.cardRecord.pin.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                HiddenRecordText(
                    text = state.cardRecord.pin,
                    labelRes = R.string.pin,
                    onLongClick = { onAction(ShowCardRecordAction.CopyToClipboard(state.cardRecord.pin, R.string.pin_copied)) },
                    hidden = !state.showPin,
                    onHiddenChanged = { onAction(ShowCardRecordAction.ShowPin(!it)) },
                    hiddenTintColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
            if (state.cardRecord.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                RecordText(
                    text = state.cardRecord.note,
                    labelRes = R.string.note,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onLongClick = { onAction(ShowCardRecordAction.CopyToClipboard(state.cardRecord.note, R.string.note_copied)) }
                )
            }
            if (state.createdDate.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                RecordText(
                    text = state.createdDate,
                    labelRes = R.string.created_at,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
            if (state.updatedDate.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                RecordText(
                    text = state.updatedDate,
                    labelRes = R.string.updated_at,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}