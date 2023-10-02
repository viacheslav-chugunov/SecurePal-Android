package viach.apps.export.model

import viach.apps.storage.model.AppSetting
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.model.CardRecord
import viach.apps.storage.model.NoteRecord

internal class DefaultExport(
    override val appSettings: List<AppSetting>,
    override val authRecords: List<AuthRecord>,
    override val noteRecords: List<NoteRecord>,
    override val cardRecords: List<CardRecord>
) : Export {
}