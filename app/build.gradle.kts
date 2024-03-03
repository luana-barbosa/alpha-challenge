plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.luanabarbosa.starswars"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.luanabarbosa.starswars"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
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

    implementation(Androidx.core)
    implementation(Androidx.appcompat)
    implementation(Androidx.material)
    implementation(Androidx.constraintlayout)
    implementation(Androidx.legacy)
    implementation(Androidx.runtime)

    implementation(Navigation.fragment)
    implementation(Navigation.ui)

    implementation(Coil.coil)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)

    implementation(Lottie.lottie)

    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.livedata)

    implementation(Koin.android)
    implementation(Koin.compose)
    implementation(Koin.core)

    implementation(Coroutines.test)
    implementation(Coroutines.coroutines)

    implementation(Mapper.mapstruct)
    implementation(Mapper.core)

    implementation(OkHttp.core)
    implementation(OkHttp.loggingInterceptor)
    implementation(OkHttp.mockWebserver)

    implementation(Paging.runtime)
    implementation(Paging.paging)

    implementation(Compose.activity)
    implementation(Compose.navigation)
    implementation(Compose.composeUI)
    implementation(Compose.composeGraphics)
    implementation(Compose.composePreview)
    implementation(Compose.composeMaterial)

    implementation(platform(Compose.composeBom))
    androidTestImplementation(platform(Compose.composeBom))

    testImplementation(AndroidTest.junit)
    androidTestImplementation(AndroidTest.testExt)
    androidTestImplementation(AndroidTest.espresso)
    androidTestImplementation(AndroidTest.junit4)
    testImplementation(AndroidTest.mockk)
    testImplementation(AndroidTest.arch)

}