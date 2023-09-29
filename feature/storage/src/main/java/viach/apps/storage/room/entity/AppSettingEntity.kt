package viach.apps.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import viach.apps.encryption.repository.EncryptionRepository
import viach.apps.storage.model.AppSetting

@Entity(tableName = "APP_SETTING")
internal class AppSettingEntity(
    @PrimaryKey
    @ColumnInfo(name = "NAME")
    override val name: String,
    @ColumnInfo(name = "VALUE")
    override val value: String
): AppSetting {

    fun encrypt(encryptionRepository: EncryptionRepository): AppSettingEntity = AppSettingEntity(
        name,
        encryptionRepository.encrypt(value)
    )

    fun decrypt(encryptionRepository: EncryptionRepository): AppSettingEntity = AppSettingEntity(
        name,
        encryptionRepository.decrypt(value)
    )

}