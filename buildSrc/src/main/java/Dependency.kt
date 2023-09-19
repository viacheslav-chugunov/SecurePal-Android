import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependency {

    object Build {
        const val COMPILE_SDK = 34
        const val APPLICATION_ID = "viach.apps.securepal"
        const val MIN_SDK = 21
        const val TARGET_SDK = 34
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1"
    }

    object Plugin {
        const val APPLICATION = "com.android.application"
        const val LIBRARY = "com.android.library"
        const val KOTLIN = "org.jetbrains.kotlin.android"
        const val KAPT = "kotlin-kapt"
        const val KSP = "com.google.devtools.ksp"
        const val HILT = "com.google.dagger.hilt.android"
        const val PARCELIZE = "kotlin-parcelize"
    }

    object Project {
        const val SHARED = ":shared"
        const val ENCRYPTION = ":feature:encryption"
        const val STORAGE = ":feature:storage"
    }

    object KotlinOptions {
        const val JVM_TARGET = "17"
    }

    object CompileOptions {
        val SOURCE_COMPATIBILITY = JavaVersion.VERSION_17
        val TARGET_COMPATIBILITY = JavaVersion.VERSION_17
    }

    object Core {
        const val CORE = "androidx.core:core-ktx:1.9.0"
        const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
    }

    object Compose {
        const val PLATFORM = "androidx.compose:compose-bom:2023.03.00"
        const val ACTIVITY = "androidx.activity:activity-compose:1.7.2"
        const val UI = "androidx.compose.ui:ui"
        const val GRAPHICS = "androidx.compose.ui:ui-graphics"
        const val PREVIEW = "androidx.compose.ui:ui-tooling-preview"
        const val MATERIAL3 = "androidx.compose.material3:material3"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling"
        const val TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
    }

    object Test {
        const val JUNIT = "junit:junit:4.13.2"
        const val JUNIT_EXT = "androidx.test.ext:junit:1.1.5"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:3.5.1"
        const val COMPOSE = "androidx.compose.ui:ui-test-junit4"
    }

    object Navigation {
        const val COMPOSE = "androidx.navigation:navigation-compose:2.7.2"
    }

    object Hilt {
        const val CORE = "com.google.dagger:hilt-android:2.44"
        const val NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val KAPT = "com.google.dagger:hilt-android-compiler:2.44"
    }

    object Room {
        const val CORE = "androidx.room:room-runtime:2.5.2"
        const val COROUTINES = "androidx.room:room-ktx:2.5.2"
        const val KSP = "androidx.room:room-compiler:2.5.2"
    }
}