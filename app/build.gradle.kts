plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(Versions.COMPILE_SDK_VERSION)
    buildToolsVersion(Versions.BUILD_TOOL_SDK)

    defaultConfig {
        applicationId(App.APPLICATION_ID)
        minSdkVersion(Versions.MIN_SDK_VERSION)
        targetSdkVersion(Versions.TARGET_SDK_VERSION)
        versionCode = App.VERSION_CODE
        versionName = App.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =  false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(Libs.ANDROID_MATIRIAL)
    implementation(Libs.APP_COMPCT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.CORE_KTS)
    implementation(Libs.JET_BRAINS)
    implementation(Libs.TEST_IMPLEMENTATION)
    androidTestImplementation(Libs.ANDROIDX_JUNIT)
    androidTestImplementation(Libs.ANDROID_EXPRESO)
    implementation(Libs.SSP)
    implementation(Libs.SDP)
    implementation(Libs.LIFECYCLE_VIEWMODEL)
    implementation(Libs.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Libs.ACTIVITY_KTX)
}