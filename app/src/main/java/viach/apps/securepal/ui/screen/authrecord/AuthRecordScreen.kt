package viach.apps.securepal.ui.screen.authrecord

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.securepal.ui.view.AuthRecordTextField

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
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { navigateBack() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_two_arrows_back),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.back))
            }
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(onClick = { onAction(AuthRecordAction.SaveAuthRecord) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_done),
                    contentDescription = null
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CutCornerShape(16.dp)
                )
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            AuthRecordTextField(
                value = state.authRecord.title,
                onValueChanged = {
                    onAction(AuthRecordAction.UpdateAuthRecord(state.authRecord.copy(title = it)))
                },
                labelRes = R.string.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthRecordTextField(
                value = state.authRecord.auth,
                onValueChanged = {
                    onAction(AuthRecordAction.UpdateAuthRecord(state.authRecord.copy(auth = it)))
                },
                labelRes = R.string.login_email_etc,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AuthRecordTextField(
                    value = state.authRecord.password,
                    onValueChanged = {
                        onAction(AuthRecordAction.UpdateAuthRecord(state.authRecord.copy(password = it)))
                    },
                    labelRes = R.string.password,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    passwordMask = !state.showPassword
                )
                IconButton(
                    onClick = { onAction(AuthRecordAction.ShowPassword(!state.showPassword)) },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = if (state.showPassword) R.drawable.ic_visible else R.drawable.ic_hidden),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            AuthRecordTextField(
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