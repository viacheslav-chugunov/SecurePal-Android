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
                saveAuthRecord(action.authRecord)
            }
        }
    }

    private fun saveAuthRecord(authRecord: AuthRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            authRecordRepository.addRecord(authRecord)
        }
    }


}