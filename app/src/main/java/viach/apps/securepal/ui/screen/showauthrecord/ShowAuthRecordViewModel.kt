package viach.apps.securepal.ui.screen.showauthrecord

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.StateViewModel
import viach.apps.storage.repository.AuthRecordRepository
import javax.inject.Inject

@HiltViewModel
class ShowAuthRecordViewModel @Inject constructor(
    private val authRecordRepository: AuthRecordRepository
) : StateViewModel<ShowAuthRecordState>(ShowAuthRecordState()) {

    fun handle(action: ShowAuthRecordAction) {
        when (action) {
            ShowAuthRecordAction.DeleteAuthRecord -> {
                viewModelScope.launch(Dispatchers.IO) {
                    authRecordRepository.removeRecord(state.authRecord)
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