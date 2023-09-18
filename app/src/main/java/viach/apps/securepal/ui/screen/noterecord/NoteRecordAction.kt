package viach.apps.securepal.ui.screen.noterecord

import viach.apps.securepal.model.NoteRecordUI

sealed interface NoteRecordAction {
    object SaveNoteRecord : NoteRecordAction
    class UpdateNoteRecord(val noteRecord: NoteRecordUI) : NoteRecordAction
}