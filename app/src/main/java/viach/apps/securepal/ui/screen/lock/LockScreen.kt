package viach.apps.securepal.ui.screen.lock

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.securepal.Screen

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun LockScreen(
    state: LockState,
    onAction: (LockAction) -> Unit,
    openScreen: (Screen) -> Unit,
    showError: (String) -> Unit
) {

    LaunchedEffect(state.inputSuccessful) {
        if (state.inputSuccessful) {
            openScreen(Screen.Dashboard)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onAction(LockAction.SendInput) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_two_arrows_next),
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            if (state.titleRes != 0) {
                Text(
                    text = stringResource(id = state.titleRes),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            TextField(
                value = state.input,
                onValueChange = { onAction(LockAction.UpdateInput(it)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}