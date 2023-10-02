package viach.apps.storage.model.export

internal class ExportStorageContent(
    val appSettings: List<ExportAppSetting>?,
    val authRecords: List<ExportAuthRecord>?,
    val noteRecords: List<ExportNoteRecord>?,
    val cardRecords: List<ExportCardRecord>?
)