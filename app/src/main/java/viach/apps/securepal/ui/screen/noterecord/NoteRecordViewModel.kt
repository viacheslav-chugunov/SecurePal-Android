package viach.apps.securepal.ui.screen.noterecord

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.StateViewModel
import viach.apps.storage.repository.NoteRecordRepository
import javax.inject.Inject

@HiltViewModel
class NoteRecordViewModel @Inject constructor(
    private val noteRecordRepository: NoteRecordRepository
) : StateViewModel<NoteRecordState>(NoteRecordState()) {

    fun handle(action: NoteRecordAction) {
        when (action) {
            NoteRecordAction.SaveNoteRecord -> {
                if (state.noteRecord.title.isBlank()) {
                    state = state.copy(titleError = true)
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        noteRecordRepository.addRecord(state.noteRecord)
                        state = state.copy(closeScreen = true)
                    }
                }
            }
            is NoteRecordAction.UpdateNoteRecord -> {
                state = state.copy(noteRecord = action.noteRecord, titleError = false)
            }
        }
    }

}