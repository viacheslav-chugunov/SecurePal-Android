package viach.apps.securepal.ui.screen.showcardrecord

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.StateViewModel
import viach.apps.shared.repository.ClipboardRepository
import viach.apps.shared.repository.TimeRepository
import viach.apps.storage.model.CardRecord
import viach.apps.storage.repository.CardRecordRepository
import javax.inject.Inject

@HiltViewModel
class ShowCardRecordViewModel @Inject constructor(
    private val cardRecordRepository: CardRecordRepository,
    private val timeRepository: TimeRepository,
    private val clipboardRepository: ClipboardRepository
) : StateViewModel<ShowCardRecordState>(ShowCardRecordState()) {
    private var isCardRecordSet: Boolean = false

    fun setCardRecord(cardRecord: CardRecord?) {
        if (isCardRecordSet || cardRecord == null) return
        isCardRecordSet = true
        val createdDate = timeRepository.createDate(cardRecord.createdAt)
        state = state.copy(cardRecord = cardRecord, createdDate = createdDate)
        viewModelScope.launch(Dispatchers.IO) {
            cardRecordRepository.getByCreatedAt(cardRecord.createdAt).collect {
                val updatedDate = if (it.createdAt != it.updatedAt) {
                    timeRepository.createDate(it.updatedAt)
                } else {
                    ""
                }
                state = state.copy(cardRecord = it, updatedDate = updatedDate)
            }
        }
    }

    fun handle(action: ShowCardRecordAction) {
        when (action) {
            is ShowCardRecordAction.CopyToClipboard -> {
                clipboardRepository.copy(action.text)
                state = state.copy(showMessageRes = action.messageRes)
            }
            ShowCardRecordAction.DeleteCardRecord -> {
                viewModelScope.launch(Dispatchers.IO) {
                    cardRecordRepository.removeRecord(state.cardRecord)
                }
            }
            ShowCardRecordAction.HandleOpenedScreen -> {
                state = state.copy(openScreen = null)
            }
            ShowCardRecordAction.HandleShownMessage -> {
                state = state.copy(showMessageRes = 0)
            }
            is ShowCardRecordAction.OpenScreen -> {
                state = state.copy(openScreen = action.screen)
            }
            is ShowCardRecordAction.ShowCheck -> {
                state = state.copy(showCheck = action.show)
            }
            is ShowCardRecordAction.ShowPin -> {
                state = state.copy(showPin = action.show)
            }
        }
    }

}