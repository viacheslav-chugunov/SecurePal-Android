package viach.apps.securepal.ui.screen.showauthrecord

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.StateViewModel
import viach.apps.securepal.model.AuthRecordUI
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.repository.AuthRecordRepository
import javax.inject.Inject

@HiltViewModel
class ShowAuthRecordViewModel @Inject constructor(
    private val authRecordRepository: AuthRecordRepository
) : StateViewModel<ShowAuthRecordState>(ShowAuthRecordState()) {
    private var isAuthRecordsSet: Boolean = false

    fun setAuthRecord(authRecord: AuthRecord?) {
        if (isAuthRecordsSet || authRecord == null) return
        isAuthRecordsSet = true
        state = state.copy(authRecord = authRecord)
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
        }
    }

}