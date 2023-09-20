package viach.apps.securepal.ui.screen.shownoterecord

import viach.apps.securepal.Screen
import viach.apps.securepal.model.NoteRecordUI
import viach.apps.storage.model.NoteRecord

data class ShowNoteRecordState(
    val noteRecord: NoteRecord = NoteRecordUI(),
    val openScreen: Screen? = null,
    val closeScreen: Boolean = false,
    val createdDate: String = "",
    val updatedDate: String = "",
    val showMessageRes: Int = 0
)