package viach.apps.storage.model

sealed interface TypedAppSetting {
    class AppTheme(val appTheme: viach.apps.storage.model.AppTheme) : TypedAppSetting
}