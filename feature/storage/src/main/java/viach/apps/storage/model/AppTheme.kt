package viach.apps.storage.model

import android.os.Build

enum class AppTheme {
    LIGHT,
    DARK,
    SYSTEM,
    DYNAMIC_LIGHT,
    DYNAMIC_DARK,
    DYNAMIC_SYSTEM;

    fun isLight(isSystemLight: Boolean) = when (this) {
        LIGHT -> true
        DARK -> false
        SYSTEM -> isSystemLight
        DYNAMIC_LIGHT -> true
        DYNAMIC_DARK -> false
        DYNAMIC_SYSTEM -> isSystemLight
    }

    companion object {
        val DEFAULT: AppTheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) SYSTEM else LIGHT
    }
}