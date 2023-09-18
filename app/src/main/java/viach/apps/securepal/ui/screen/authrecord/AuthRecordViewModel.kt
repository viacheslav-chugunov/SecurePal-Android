package viach.apps.securepal.ui.screen.authrecord

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.StateViewModel
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.repository.AuthRecordRepository
import javax.inject.Inject

@HiltViewModel
class AuthRecordViewModel @Inject constructor(
    private val authRecordRepository: AuthRecordRepository
) : StateViewModel<AuthRecordState>(AuthRecordState()) {

    fun handle(action: AuthRecordAction) {
        when (action) {
            is AuthRecordAction.SaveAuthRecord -> {
                if (state.authRecord.title.isBlank()) {
                    state = state.copy(titleError = true)
                } else if (state.authRecord.auth.isBlank()) {
                    state = state.copy(authError = true)
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        authRecordRepository.addRecord(state.authRecord)
                        state = state.copy(closeScreen = true)
                    }
                }
            }
            is AuthRecordAction.UpdateAuthRecord -> {
                state = state.copy(authRecord = action.authRecord, authError = false, titleError = false)
            }
            is AuthRecordAction.ShowPassword -> {
                state = state.copy(showPassword = action.show)
            }
        }
    }
}