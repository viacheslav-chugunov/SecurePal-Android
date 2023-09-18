package viach.apps.securepal.ui.screen.cardrecord

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
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.securepal.ui.view.HiddenRecordTextField
import viach.apps.securepal.ui.view.RecordTextField
import viach.apps.securepal.ui.view.RecordTopBar

@Composable
fun CardRecordScreen(
    state: CardRecordState,
    onAction: (CardRecordAction) -> Unit,
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
            doneContainerColor = MaterialTheme.colorScheme.tertiary,
            doneContentColor = MaterialTheme.colorScheme.onTertiary,
            onNavigateBack = navigateBack,
            onDone = { onAction(CardRecordAction.SaveCardRecord) }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = CutCornerShape(16.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            RecordTextField(
                value = state.cardRecord.title,
                onValueChanged = { onAction(CardRecordAction.UpdateCardRecord(state.cardRecord.copy(title = it))) },
                labelRes = R.string.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                error = state.titleError
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecordTextField(
                value = state.cardRecord.owner,
                onValueChanged = { onAction(CardRecordAction.UpdateCardRecord(state.cardRecord.copy(owner = it))) },
                labelRes = R.string.owner,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecordTextField(
                value = state.cardRecord.number,
                onValueChanged = { onAction(CardRecordAction.UpdateCardRecord(state.cardRecord.copy(number = it))) },
                labelRes = R.string.card_number,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                error = state.numberError
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecordTextField(
                value = state.cardRecord.expiration,
                onValueChanged = { onAction(CardRecordAction.UpdateCardRecord(state.cardRecord.copy(expiration = it))) },
                labelRes = R.string.expiration_date,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HiddenRecordTextField(
                value = state.cardRecord.check,
                onValueChanged = { onAction(CardRecordAction.UpdateCardRecord(state.cardRecord.copy(check = it))) },
                labelRes = R.string.verification_number,
                hidden = !state.showCheck,
                hiddenTintColor = MaterialTheme.colorScheme.onTertiaryContainer,
                onHiddenChanged = { onAction(CardRecordAction.ShowCheck(!it)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            HiddenRecordTextField(
                value = state.cardRecord.pin,
                onValueChanged = { onAction(CardRecordAction.UpdateCardRecord(state.cardRecord.copy(pin = it))) },
                labelRes = R.string.pin,
                hidden = !state.showPin,
                hiddenTintColor = MaterialTheme.colorScheme.onTertiaryContainer,
                onHiddenChanged = { onAction(CardRecordAction.ShowPin(!it)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecordTextField(
                value = state.cardRecord.note,
                onValueChanged = { onAction(CardRecordAction.UpdateCardRecord(state.cardRecord.copy(note = it))) },
                labelRes = R.string.note,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}