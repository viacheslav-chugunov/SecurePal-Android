package viach.apps.securepal.ui.view

import android.os.Build
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.storage.model.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppThemeBottomSheet(
    show: Boolean,
    selectedAppTheme: AppTheme,
    onDismiss: () -> Unit,
    onAppThemeSelected: (AppTheme) -> Unit
) {
    if (!show) return

    val stateSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = stateSheet
    ) {
        Text(
            text = stringResource(id = R.string.select_app_theme),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        BottomSheetItem(
            titleRes = R.string.light,
            isSelected = selectedAppTheme == AppTheme.LIGHT,
            onClick = {
                stateSheet.hide()
                onDismiss()
                onAppThemeSelected(AppTheme.LIGHT)
            }
        )
        BottomSheetItem(
            titleRes = R.string.dark,
            isSelected = selectedAppTheme == AppTheme.DARK,
            onClick = {
                stateSheet.hide()
                onDismiss()
                onAppThemeSelected(AppTheme.DARK)
            }
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            BottomSheetItem(
                titleRes = R.string.system,
                isSelected = selectedAppTheme == AppTheme.SYSTEM,
                onClick = {
                    stateSheet.hide()
                    onDismiss()
                    onAppThemeSelected(AppTheme.SYSTEM)
                }
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            BottomSheetItem(
                titleRes = R.string.dynamic_light,
                isSelected = selectedAppTheme == AppTheme.DYNAMIC_LIGHT,
                onClick = {
                    stateSheet.hide()
                    onDismiss()
                    onAppThemeSelected(AppTheme.DYNAMIC_LIGHT)
                }
            )
            BottomSheetItem(
                titleRes = R.string.dynamic_dark,
                isSelected = selectedAppTheme == AppTheme.DYNAMIC_DARK,
                onClick = {
                    stateSheet.hide()
                    onDismiss()
                    onAppThemeSelected(AppTheme.DYNAMIC_DARK)
                }
            )
            BottomSheetItem(
                titleRes = R.string.dynamic_system,
                isSelected = selectedAppTheme == AppTheme.DYNAMIC_SYSTEM,
                onClick = {
                    stateSheet.hide()
                    onDismiss()
                    onAppThemeSelected(AppTheme.DYNAMIC_SYSTEM)
                }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}