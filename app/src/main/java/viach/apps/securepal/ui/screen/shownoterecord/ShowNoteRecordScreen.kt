package viach.apps.securepal.ui.screen.shownoterecord

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
import viach.apps.securepal.ui.view.RecordText
import viach.apps.securepal.ui.view.ShowRecordTopBar

@Composable
fun ShowNoteRecordScreen(
    state: ShowNoteRecordState,
    onAction: (ShowNoteRecordAction) -> Unit,
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
            onAction(ShowNoteRecordAction.HandleOpenedScreen)
        }
    }

    LaunchedEffect(state.showMessageRes) {
        if (state.showMessageRes != 0) {
            showMessage(context.getString(state.showMessageRes))
            onAction(ShowNoteRecordAction.HandleShownMessage)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShowRecordTopBar(
            editContainerColor = MaterialTheme.colorScheme.secondary,
            editContentColor = MaterialTheme.colorScheme.onSecondary,
            onNavigateBack = navigateBack,
            onEditClick = { onAction(ShowNoteRecordAction.OpenScreen(Screen.EditNoteRecord(state.noteRecord))) },
            onDeleteClick = { onAction(ShowNoteRecordAction.DeleteNoteRecord) }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = CutCornerShape(16.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            RecordText(
                text = state.noteRecord.title,
                labelRes = R.string.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onLongClick = { onAction(ShowNoteRecordAction.CopyToClipboard(state.noteRecord.title, R.string.title_copied)) }
            )
            if (state.noteRecord.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                RecordText(
                    text = state.noteRecord.note,
                    labelRes = R.string.note,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onLongClick = { onAction(ShowNoteRecordAction.CopyToClipboard(state.noteRecord.note, R.string.note_copied)) }
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