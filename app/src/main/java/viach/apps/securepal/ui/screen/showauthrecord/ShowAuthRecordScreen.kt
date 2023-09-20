package viach.apps.securepal.ui.screen.showauthrecord

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
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.securepal.Screen
import viach.apps.securepal.ui.view.HiddenRecordText
import viach.apps.securepal.ui.view.RecordText
import viach.apps.securepal.ui.view.ShowRecordTopBar

@Composable
fun ShowAuthRecordScreen(
    state: ShowAuthRecordState,
    onAction: (ShowAuthRecordAction) -> Unit,
    openScreen: (Screen) -> Unit,
    navigateBack: () -> Unit,
    showMessage: (String) -> Unit
) {

    LaunchedEffect(state.closeScreen) {
        if (state.closeScreen) {
            navigateBack()
        }
    }

    LaunchedEffect(state.openScreen) {
        state.openScreen?.let {
            openScreen(it)
            onAction(ShowAuthRecordAction.HandleOpenedScreen)
        }
    }

    LaunchedEffect(state.showMessage) {
        if (state.showMessage.isNotEmpty()) {
            showMessage(state.showMessage)
            onAction(ShowAuthRecordAction.HandleShownMessage)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShowRecordTopBar(
            editContainerColor = MaterialTheme.colorScheme.primary,
            editContentColor = MaterialTheme.colorScheme.onPrimary,
            onNavigateBack = navigateBack,
            onEditClick = { onAction(ShowAuthRecordAction.OpenScreen(Screen.EditAuthRecord(state.authRecord))) },
            onDeleteClick = { onAction(ShowAuthRecordAction.DeleteAuthRecord) }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CutCornerShape(16.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            RecordText(
                text = state.authRecord.title,
                labelRes = R.string.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onLongClick = { onAction(ShowAuthRecordAction.CopyToClipboard(state.authRecord.title)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecordText(
                text = state.authRecord.auth,
                labelRes = R.string.auth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onLongClick = { onAction(ShowAuthRecordAction.CopyToClipboard(state.authRecord.auth)) }
            )
            if (state.authRecord.password.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                HiddenRecordText(
                    text = state.authRecord.password,
                    labelRes = R.string.password,
                    hidden = !state.showPassword,
                    onHiddenChanged = { onAction(ShowAuthRecordAction.ShowPassword(!it)) },
                    hiddenTintColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    onLongClick = { onAction(ShowAuthRecordAction.CopyToClipboard(state.authRecord.password)) }
                )
            }
            if (state.authRecord.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                RecordText(
                    text = state.authRecord.note,
                    labelRes = R.string.note,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onLongClick = { onAction(ShowAuthRecordAction.CopyToClipboard(state.authRecord.note)) }
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