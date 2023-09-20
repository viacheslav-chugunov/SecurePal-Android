package viach.apps.securepal.ui.screen.noterecord

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.R
import viach.apps.securepal.StateViewModel
import viach.apps.securepal.model.NoteRecordUI
import viach.apps.storage.model.NoteRecord
import viach.apps.storage.repository.NoteRecordRepository
import javax.inject.Inject

@HiltViewModel
class NoteRecordViewModel @Inject constructor(
    private val noteRecordRepository: NoteRecordRepository
) : StateViewModel<NoteRecordState>(NoteRecordState()) {
    private var isEditableNoteRecordSet: Boolean = false

    fun setEditableNoteRecord(noteRecord: NoteRecord?) {
        if (isEditableNoteRecordSet || noteRecord == null) return
        isEditableNoteRecordSet = true
        state = state.copy(noteRecord = NoteRecordUI(noteRecord))
    }

    fun handle(action: NoteRecordAction) {
        when (action) {
            NoteRecordAction.SaveNoteRecord -> {
                if (state.noteRecord.title.isBlank()) {
                    state = state.copy(titleError = true, errorMessageRes = R.string.title_required)
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        if (isEditableNoteRecordSet) {
                            noteRecordRepository.updateRecord(state.noteRecord.copy(updatedAt = System.currentTimeMillis()))
                        } else {
                            noteRecordRepository.addRecord(state.noteRecord)
                        }
                        state = state.copy(closeScreen = true)
                    }
                }
            }
            is NoteRecordAction.UpdateNoteRecord -> {
                state = state.copy(
                    noteRecord = action.noteRecord,
                    titleError = false,
                    errorMessageRes = 0
                )
            }
        }
    }

}