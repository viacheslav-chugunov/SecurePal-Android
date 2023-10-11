import java.util.Properties

plugins {
    id(Dependency.Plugin.APPLICATION)
    id(Dependency.Plugin.KOTLIN)
    id(Dependency.Plugin.KAPT)
    id(Dependency.Plugin.HILT)
    id(Dependency.Plugin.PARCELIZE)
}

val privateProperties = Properties().apply {
    try {
        load(rootDir.resolve("private.properties").inputStream())
    } catch (e: java.io.FileNotFoundException) {
        Properties()
    }
}

val encryptionToken: String = privateProperties.getProperty("encryptionToken", "0000000000000000")
val signingKeystorePassword: String = privateProperties.getProperty("signingKeystorePassword", "")
val signingKeyPassword: String = privateProperties.getProperty("signingKeyPassword", "")

android {
    namespace = "viach.apps.securepal"
    compileSdk = Dependency.Build.COMPILE_SDK

    defaultConfig {
        applicationId = Dependency.Build.APPLICATION_ID
        minSdk = Dependency.Build.MIN_SDK
        targetSdk = Dependency.Build.TARGET_SDK
        versionCode = Dependency.Build.VERSION_CODE
        versionName = Dependency.Build.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "ENCRYPTION_TOKEN", "\"${encryptionToken}\"")
    }
    signingConfigs {
        register("release") {
            storeFile = file("../secure-pal-keystore.jks")
            storePassword = signingKeystorePassword
            keyAlias = "secure-pal-key"
            keyPassword = signingKeyPassword
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            postprocessing {
                isObfuscate = false
                isOptimizeCode = true
                isRemoveUnusedCode = true
                isRemoveUnusedResources = true
                file("proguard").listFiles()?.forEach { proguardFile(it) }
            }

            signingConfig = signingConfigs["release"]
        }
        debug {
            postprocessing {
                isObfuscate = false
                isOptimizeCode = false
                isRemoveUnusedCode = false
                isRemoveUnusedResources = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = Dependency.CompileOptions.SOURCE_COMPATIBILITY
        targetCompatibility = Dependency.CompileOptions.TARGET_COMPATIBILITY
    }

    kotlinOptions {
        jvmTarget = Dependency.KotlinOptions.JVM_TARGET
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Dependency.Project.SHARED))
    implementation(project(Dependency.Project.STORAGE))
    implementation(project(Dependency.Project.EXPORT))
    implementation(project(Dependency.Project.LOCK))

    implementation(Dependency.Core.CORE)
    implementation(Dependency.Core.LIFECYCLE)
    implementation(Dependency.Compose.ACTIVITY)
    implementation(platform(Dependency.Compose.PLATFORM))
    implementation(Dependency.Compose.UI)
    implementation(Dependency.Compose.GRAPHICS)
    implementation(Dependency.Compose.PREVIEW)
    implementation(Dependency.Compose.MATERIAL3)
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.JUNIT_EXT)
    androidTestImplementation(Dependency.Test.ESPRESSO)
    androidTestImplementation(platform(Dependency.Compose.PLATFORM))
    androidTestImplementation(Dependency.Test.COMPOSE)
    debugImplementation(Dependency.Compose.UI_TOOLING)
    debugImplementation(Dependency.Compose.TEST_MANIFEST)
    implementation(Dependency.Navigation.COMPOSE)
    implementation(Dependency.Hilt.CORE)
    implementation(Dependency.Hilt.NAVIGATION_COMPOSE)
    kapt(Dependency.Hilt.KAPT)
}