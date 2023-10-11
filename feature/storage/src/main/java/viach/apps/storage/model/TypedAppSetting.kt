package viach.apps.storage.model

sealed interface TypedAppSetting {
    class AppTheme(val appTheme: viach.apps.storage.model.AppTheme) : TypedAppSetting
    class Lock(val lock: viach.apps.storage.model.Lock) : TypedAppSetting
}