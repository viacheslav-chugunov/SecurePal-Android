package viach.apps.storage.model.export

import viach.apps.storage.model.AppSetting
import viach.apps.storage.room.entity.AppSettingEntity

internal class ExportAppSetting(
    val name: String?,
    val value: String?
) {

    constructor(appSetting: AppSetting) : this(appSetting.name, appSetting.value)

    fun toEntity(): AppSettingEntity = AppSettingEntity(
        name ?: "",
        value ?: ""
    )

}