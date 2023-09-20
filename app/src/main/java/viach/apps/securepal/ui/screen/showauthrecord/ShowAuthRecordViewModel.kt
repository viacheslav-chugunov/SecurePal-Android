package viach.apps.securepal.ui.screen.showauthrecord

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.R
import viach.apps.securepal.StateViewModel
import viach.apps.securepal.model.AuthRecordUI
import viach.apps.shared.repository.ClipboardRepository
import viach.apps.shared.repository.TimeRepository
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.repository.AuthRecordRepository
import javax.inject.Inject

@HiltViewModel
class ShowAuthRecordViewModel @Inject constructor(
    private val authRecordRepository: AuthRecordRepository,
    private val timeRepository: TimeRepository,
    private val clipboardRepository: ClipboardRepository
) : StateViewModel<ShowAuthRecordState>(ShowAuthRecordState()) {
    private var isAuthRecordsSet: Boolean = false

    fun setAuthRecord(authRecord: AuthRecord?) {
        if (isAuthRecordsSet || authRecord == null) return
        isAuthRecordsSet = true
        val createdDate = timeRepository.createDate(authRecord.createdAt, "dd MMMM yyyy, HH:mm")
        state = state.copy(authRecord = authRecord, createdDate = createdDate)
        viewModelScope.launch(Dispatchers.IO) {
            authRecordRepository.getByCreatedAt(authRecord.createdAt).collect {
                val updatedDate = if (it.createdAt != it.updatedAt) {
                    timeRepository.createDate(it.updatedAt, "dd MMMM yyyy, HH:mm")
                } else {
                    ""
                }
                state = state.copy(authRecord = it, updatedDate = updatedDate)
            }
        }
    }

    fun handle(action: ShowAuthRecordAction) {
        when (action) {
            ShowAuthRecordAction.DeleteAuthRecord -> {
                viewModelScope.launch(Dispatchers.IO) {
                    authRecordRepository.removeRecord(state.authRecord)
                    state = state.copy(closeScreen = true)
                }
            }
            ShowAuthRecordAction.HandleOpenedScreen -> {
                state = state.copy(openScreen = null)
            }
            is ShowAuthRecordAction.OpenScreen -> {
                state = state.copy(openScreen = action.screen)
            }
            is ShowAuthRecordAction.ShowPassword -> {
                state = state.copy(showPassword = action.show)
            }
            is ShowAuthRecordAction.CopyToClipboard -> {
                clipboardRepository.copy(action.text)
                state = state.copy(showMessageRes = action.messageRes)
            }
            ShowAuthRecordAction.HandleShownMessage -> {
                state = state.copy(showMessageRes = 0)
            }
        }
    }

}