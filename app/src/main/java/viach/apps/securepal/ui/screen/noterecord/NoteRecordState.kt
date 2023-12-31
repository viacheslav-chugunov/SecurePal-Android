package viach.apps.securepal.ui.screen.noterecord

import viach.apps.securepal.model.NoteRecordUI

data class NoteRecordState(
    val noteRecord: NoteRecordUI = NoteRecordUI(),
    val closeScreen: Boolean = false,
    val titleError: Boolean = false,
    val errorMessageRes: Int = 0
)