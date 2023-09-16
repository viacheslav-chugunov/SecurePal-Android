package viach.apps.securepal.ui.screen.dashboard

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import viach.apps.securepal.Screen
import viach.apps.securepal.StateViewModel
import viach.apps.storage.repository.AuthRecordRepository
import viach.apps.storage.repository.CardRecordRepository
import viach.apps.storage.repository.NoteRecordRepository
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRecordRepository: AuthRecordRepository,
    private val noteRecordRepository: NoteRecordRepository,
    private val cardRecordRepository: CardRecordRepository
) : StateViewModel<DashboardState>(DashboardState()) {

    init {
        collectAuthRecords()
        collectNoteRecords()
        collectCardRecords()
    }

    private fun collectAuthRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            authRecordRepository.getAll().collect { authRecords ->
                state = state.copy(authRecords = authRecords)
            }
        }
    }

    private fun collectNoteRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            noteRecordRepository.getAll().collect { noteRecords ->
                state = state.copy(noteRecords = noteRecords)
            }
        }
    }

    private fun collectCardRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            cardRecordRepository.getAll().collect { cardRecords ->
                state = state.copy(cardRecords = cardRecords)
            }
        }
    }

    fun handle(action: DashboardAction) {
        when (action) {
            DashboardAction.HandleOpenedScreen -> {
                state = state.copy(openScreen = null)
            }
            is DashboardAction.OpenScreen -> {
                state = state.copy(openScreen = action.screen)
            }
        }
    }

}