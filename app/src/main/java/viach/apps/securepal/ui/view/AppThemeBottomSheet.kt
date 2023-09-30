package viach.apps.securepal.ui.view

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import viach.apps.securepal.R
import viach.apps.storage.model.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppThemeBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    onAppThemeSelected: (AppTheme) -> Unit
) {
    if (!show) return

    val stateSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = stateSheet
    ) {
        Text(
            text = stringResource(id = R.string.select_app_theme),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.light),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        stateSheet.hide()
                        onDismiss()
                        onAppThemeSelected(AppTheme.LIGHT)
                    }
                }
                .padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.dark),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        stateSheet.hide()
                        onDismiss()
                        onAppThemeSelected(AppTheme.DARK)
                    }
                }
                .padding(16.dp)
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Text(
                text = stringResource(id = R.string.system),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        coroutineScope.launch {
                            stateSheet.hide()
                            onDismiss()
                            onAppThemeSelected(AppTheme.SYSTEM)
                        }
                    }
                    .padding(16.dp)
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Text(
                text = stringResource(id = R.string.dynamic_light),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        coroutineScope.launch {
                            stateSheet.hide()
                            onDismiss()
                            onAppThemeSelected(AppTheme.DYNAMIC_LIGHT)
                        }
                    }
                    .padding(16.dp)
            )
            Text(
                text = stringResource(id = R.string.dynamic_dark),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        coroutineScope.launch {
                            stateSheet.hide()
                            onDismiss()
                            onAppThemeSelected(AppTheme.DYNAMIC_DARK)
                        }
                    }
                    .padding(16.dp)
            )
            Text(
                text = stringResource(id = R.string.dynamic_system),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        coroutineScope.launch {
                            stateSheet.hide()
                            onDismiss()
                            onAppThemeSelected(AppTheme.DYNAMIC_SYSTEM)
                        }
                    }
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}