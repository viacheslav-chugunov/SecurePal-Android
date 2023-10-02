package viach.apps.storage.model.export

import viach.apps.storage.room.entity.AppSettingEntity

internal class ExportAppSetting(
    val name: String?,
    val value: String?
) {

    fun toEntity(): AppSettingEntity = AppSettingEntity(
        name ?: "",
        value ?: ""
    )

}