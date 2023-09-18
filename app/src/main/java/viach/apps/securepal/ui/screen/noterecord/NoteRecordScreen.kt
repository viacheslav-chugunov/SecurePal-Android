package viach.apps.securepal.ui.screen.noterecord

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
import viach.apps.securepal.ui.view.RecordTextField
import viach.apps.securepal.ui.view.RecordTopBar

@Composable
fun NoteRecordScreen(
    state: NoteRecordState,
    onAction: (NoteRecordAction) -> Unit,
    navigateBack: () -> Unit,
    showError: (String) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state.closeScreen) {
        if (state.closeScreen) {
            navigateBack()
        }
    }

    LaunchedEffect(state.errorMessageRes) {
        if (state.errorMessageRes != 0) {
            showError(context.getString(state.errorMessageRes))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RecordTopBar(
            doneContainerColor = MaterialTheme.colorScheme.secondary,
            doneContentColor = MaterialTheme.colorScheme.onSecondary,
            onNavigateBack = navigateBack,
            onDone = { onAction(NoteRecordAction.SaveNoteRecord) }
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
            RecordTextField(
                value = state.noteRecord.title,
                onValueChanged = {
                    onAction(NoteRecordAction.UpdateNoteRecord(state.noteRecord.copy(title = it)))
                },
                labelRes = R.string.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                error = state.titleError
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecordTextField(
                value = state.noteRecord.note,
                onValueChanged = {
                    onAction(NoteRecordAction.UpdateNoteRecord(state.noteRecord.copy(note = it)))
                },
                labelRes = R.string.note,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                multiline = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}