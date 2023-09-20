package viach.apps.securepal.ui.screen.shownoterecord

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.StateViewModel
import viach.apps.shared.repository.ClipboardRepository
import viach.apps.shared.repository.TimeRepository
import viach.apps.storage.model.NoteRecord
import viach.apps.storage.repository.NoteRecordRepository
import javax.inject.Inject

@HiltViewModel
class ShowNoteRecordViewModel @Inject constructor(
    private val noteRecordRepository: NoteRecordRepository,
    private val timeRepository: TimeRepository,
    private val clipboardRepository: ClipboardRepository
) : StateViewModel<ShowNoteRecordState>(ShowNoteRecordState()) {
    private var isNoteRecordSet: Boolean = false

    fun setNoteRecord(noteRecord: NoteRecord?) {
        if (isNoteRecordSet || noteRecord == null) return
        isNoteRecordSet = true
        val createdDate = timeRepository.createDate(noteRecord.createdAt)
        state = state.copy(noteRecord = noteRecord, createdDate = createdDate)
        viewModelScope.launch(Dispatchers.IO) {
            noteRecordRepository.getByCreatedAt(noteRecord.createdAt).collect {
                val updatedDate = if (it.createdAt != it.updatedAt) {
                    timeRepository.createDate(it.updatedAt)
                } else {
                    ""
                }
                state = state.copy(noteRecord = it, updatedDate = updatedDate)
            }
        }
    }

    fun handle(action: ShowNoteRecordAction) {
        when (action) {
            is ShowNoteRecordAction.CopyToClipboard -> {
                clipboardRepository.copy(action.text)
                state = state.copy(showMessageRes = action.messageRes)
            }
            ShowNoteRecordAction.DeleteNoteRecord -> {
                viewModelScope.launch(Dispatchers.IO) {
                    noteRecordRepository.removeRecord(state.noteRecord)
                    state = state.copy(closeScreen = true)
                }
            }
            ShowNoteRecordAction.HandleOpenedScreen -> {
                state = state.copy(openScreen = null)
            }
            ShowNoteRecordAction.HandleShownMessage -> {
                state = state.copy(showMessageRes = 0)
            }
            is ShowNoteRecordAction.OpenScreen -> {
                state = state.copy(openScreen = action.screen)
            }
        }
    }

}