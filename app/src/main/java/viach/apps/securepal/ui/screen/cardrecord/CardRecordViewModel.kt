package viach.apps.securepal.ui.screen.cardrecord

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.R
import viach.apps.securepal.StateViewModel
import viach.apps.securepal.model.CardRecordUI
import viach.apps.storage.model.CardRecord
import viach.apps.storage.repository.CardRecordRepository
import javax.inject.Inject

@HiltViewModel
class CardRecordViewModel @Inject constructor(
    private val cardRecordRepository: CardRecordRepository
) : StateViewModel<CardRecordState>(CardRecordState()) {
    private var isEditableCardRecordSet: Boolean = false

    fun setEditableCardRecord(cardRecord: CardRecord?) {
        if (isEditableCardRecordSet || cardRecord == null) return
        isEditableCardRecordSet = true
        state = state.copy(cardRecord = CardRecordUI(cardRecord))
    }

    fun handle(action: CardRecordAction) {
        when (action) {
            CardRecordAction.SaveCardRecord -> {
                if (state.cardRecord.title.isBlank()) {
                    state = state.copy(titleError = true, errorMessageRes = R.string.title_required)
                } else if (state.cardRecord.number.isBlank()) {
                    state = state.copy(numberError = true, errorMessageRes = R.string.card_number_required)
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        if (isEditableCardRecordSet) {
                            cardRecordRepository.updateRecord(state.cardRecord.copy(updatedAt = System.currentTimeMillis()))
                        } else {
                            cardRecordRepository.addRecord(state.cardRecord)
                        }
                        state = state.copy(closeScreen = true)
                    }
                }
            }
            is CardRecordAction.ShowCheck -> {
                state = state.copy(showCheck = action.show)
            }
            is CardRecordAction.ShowPin -> {
                state = state.copy(showPin = action.show)
            }
            is CardRecordAction.UpdateCardRecord -> {
                state = state.copy(
                    cardRecord = action.cardRecord,
                    titleError = false,
                    numberError = false,
                    errorMessageRes = 0
                )
            }
        }
    }

}