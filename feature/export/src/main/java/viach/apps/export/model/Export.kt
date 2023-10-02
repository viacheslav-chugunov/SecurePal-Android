package viach.apps.export.model

import viach.apps.storage.model.AppSetting
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.model.CardRecord
import viach.apps.storage.model.NoteRecord

interface Export {
    val appSettings: List<AppSetting>
    val authRecords: List<AuthRecord>
    val noteRecords: List<NoteRecord>
    val cardRecords: List<CardRecord>
}