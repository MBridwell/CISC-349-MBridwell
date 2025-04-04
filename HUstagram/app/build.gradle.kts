plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.hustagram"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hustagram"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // CameraX core library
    implementation("androidx.camera:camera-core:1.2.0")

    // CameraX Camera2 implementation
    implementation("androidx.camera:camera-camera2:1.2.0")

    // CameraX View library for PreviewView
    implementation("androidx.camera:camera-view:1.2.0")

}