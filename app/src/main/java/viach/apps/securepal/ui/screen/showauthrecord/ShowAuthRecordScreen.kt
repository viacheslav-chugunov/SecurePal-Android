package viach.apps.securepal.ui.screen.showauthrecord

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import viach.apps.securepal.Screen

@Composable
fun ShowAuthRecordScreen(
    state: ShowAuthRecordState,
    onAction: (ShowAuthRecordAction) -> Unit,
    openScreen: (Screen) -> Unit,
    navigateBack: () -> Unit
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}