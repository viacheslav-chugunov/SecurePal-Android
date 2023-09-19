package viach.apps.securepal.ui.screen.showauthrecord

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viach.apps.securepal.Screen
import viach.apps.securepal.ui.view.ShowRecordTopBar

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

        }
    }
}