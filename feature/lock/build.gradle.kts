plugins {
    id(Dependency.Plugin.LIBRARY)
    id(Dependency.Plugin.KOTLIN)
    id(Dependency.Plugin.HILT)
    id(Dependency.Plugin.KAPT)
}

android {
    namespace = "viach.apps.lock"
    compileSdk = Dependency.Build.COMPILE_SDK

    defaultConfig {
        minSdk = Dependency.Build.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Dependency.CompileOptions.SOURCE_COMPATIBILITY
        targetCompatibility = Dependency.CompileOptions.TARGET_COMPATIBILITY
    }
    kotlinOptions {
        jvmTarget = Dependency.KotlinOptions.JVM_TARGET
    }
}

dependencies {
    implementation(project(Dependency.Project.SHARED))
    implementation(project(Dependency.Project.STORAGE))

    implementation(Dependency.Core.CORE)
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.JUNIT_EXT)
    androidTestImplementation(Dependency.Test.ESPRESSO)
    implementation(Dependency.Hilt.CORE)
    implementation(Dependency.Gson.CORE)
    kapt(Dependency.Hilt.KAPT)
}