package viach.apps.securepal.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.securepal.extension.openLink
import viach.apps.securepal.ui.view.AppThemeBottomSheet
import viach.apps.storage.model.AppTheme

@Composable
fun SettingsScreen(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
    val context = LocalContext.current

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CutCornerShape(16.dp)
                    )
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.app_settings),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CutCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                        .clickable { onAction(SettingsAction.ShowAppThemeBottomSheet(true)) }
                        .padding(all = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.theme),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(id = when (state.appTheme) {
                            AppTheme.LIGHT -> R.string.light
                            AppTheme.DARK -> R.string.dark
                            AppTheme.SYSTEM -> R.string.system
                            AppTheme.DYNAMIC_LIGHT -> R.string.dynamic_light
                            AppTheme.DYNAMIC_DARK -> R.string.dynamic_dark
                            AppTheme.DYNAMIC_SYSTEM -> R.string.dynamic_system
                        })
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = CutCornerShape(16.dp)
                    )
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.additional),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.privacy_policy),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CutCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                        .clickable { context.openLink("https://docs.google.com/document/d/1P2vxnY85dxue9nPfDGmUfKFuq3LKnHEhCgDtXpJ60MQ/edit") }
                        .padding(all = 16.dp)
                )
            }
        }
        AppThemeBottomSheet(
            show = state.showAppThemeBottomSheet,
            selectedAppTheme = state.appTheme,
            onDismiss = { onAction(SettingsAction.ShowAppThemeBottomSheet(false)) },
            onAppThemeSelected = { onAction(SettingsAction.SetAppTheme(it)) }
        )
    }


}