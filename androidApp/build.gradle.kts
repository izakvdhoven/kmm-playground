plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("kotlin-android-extensions")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("com.google.android.material:material:1.4.0")
                implementation("androidx.appcompat:appcompat:1.3.1")
                implementation("androidx.constraintlayout:constraintlayout:2.1.1")
                implementation("androidx.core:core-ktx:1.6.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
                implementation("androidx.annotation:annotation:1.2.0")
                implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
                implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
                implementation("io.insert-koin:koin-android:3.1.1")
            }
        }
    }
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "za.co.izakvdhoven.kmmplayground.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
}