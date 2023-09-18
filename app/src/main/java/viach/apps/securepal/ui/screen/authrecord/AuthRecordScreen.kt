package viach.apps.securepal.ui.screen.authrecord

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.securepal.ui.view.HiddenRecordTextField
import viach.apps.securepal.ui.view.RecordTextField
import viach.apps.securepal.ui.view.RecordTopBar

@Composable
fun AuthRecordScreen(
    state: AuthRecordState,
    onAction: (AuthRecordAction) -> Unit,
    navigateBack: () -> Unit
) {

    LaunchedEffect(state.closeScreen) {
        if (state.closeScreen) {
            navigateBack()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RecordTopBar(
            doneContainerColor = MaterialTheme.colorScheme.primary,
            doneContentColor = MaterialTheme.colorScheme.onPrimary,
            onNavigateBack = navigateBack,
            onDone = { onAction(AuthRecordAction.SaveAuthRecord) }
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
            RecordTextField(
                value = state.authRecord.title,
                onValueChanged = {
                    onAction(AuthRecordAction.UpdateAuthRecord(state.authRecord.copy(title = it)))
                },
                labelRes = R.string.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                error = state.titleError
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecordTextField(
                value = state.authRecord.auth,
                onValueChanged = {
                    onAction(AuthRecordAction.UpdateAuthRecord(state.authRecord.copy(auth = it)))
                },
                labelRes = R.string.login_email_etc,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                error = state.authError
            )
            Spacer(modifier = Modifier.height(16.dp))
            HiddenRecordTextField(
                value = state.authRecord.password,
                onValueChanged = {
                    onAction(AuthRecordAction.UpdateAuthRecord(state.authRecord.copy(password = it)))
                },
                labelRes = R.string.password,
                hidden = !state.showPassword,
                hiddenTintColor = MaterialTheme.colorScheme.onPrimaryContainer,
                onHiddenChanged = { onAction(AuthRecordAction.ShowPassword(!it)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecordTextField(
                value = state.authRecord.note,
                onValueChanged = {
                    onAction(AuthRecordAction.UpdateAuthRecord(state.authRecord.copy(note = it)))
                },
                labelRes = R.string.note,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}