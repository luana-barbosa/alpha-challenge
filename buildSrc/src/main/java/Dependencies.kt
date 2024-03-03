object Navigation {
    private const val version = "2.5.3"

    const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
    const val ui = "androidx.navigation:navigation-ui-ktx:$version"
}

object Coil {
    private const val version = "2.3.0"

    const val coil = "io.coil-kt:coil-compose:$version"
}

object Retrofit {
    private const val versionRetrofit = "2.9.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:$versionRetrofit"
    const val gson = "com.squareup.retrofit2:converter-gson:$versionRetrofit"
}

object Lottie {
    private const val version = "3.7.2"

    const val lottie = "com.airbnb.android:lottie:$version"
}

object Lifecycle {
    private const val version = "2.5.1"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
}

object Koin {
    private const val version = "3.5.3"

    const val android = "io.insert-koin:koin-android:$version"
    const val compose = "io.insert-koin:koin-androidx-compose:$version"
    const val core = "io.insert-koin:koin-core:$version"
}

object Coroutines {
    private const val version = "1.6.3"

    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
}

object Androidx {
    private const val version = "1.8.0"
    private const val versionAppCompat = "1.6.1"
    private const val versionMaterial = "1.10.0"
    private const val versionConstraint = "2.1.4"
    private const val versionLegacy = "1.0.0"
    private const val versionSwipeRefresh = "1.1.0"
    private const val versionRuntime = "2.6.2"

    const val core = "androidx.core:core-ktx:${version}"
    const val appcompat = "androidx.appcompat:appcompat:${versionAppCompat}"
    const val material = "com.google.android.material:material:${versionMaterial}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${versionConstraint}"
    const val legacy = "androidx.legacy:legacy-support-v4:${versionLegacy}"
    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${versionSwipeRefresh}"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${versionRuntime}"

}

object AndroidTest {
    private const val junitVersion = "4.13.2"
    private const val testVersion = "1.1.5"
    private const val espressoVersion = "3.5.1"
    private const val mockkVersion = "1.12.0"
    private const val archVersion = "2.1.0"

    const val junit = "junit:junit:${junitVersion}"
    const val testExt = "androidx.test.ext:junit:${testVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${espressoVersion}"
    const val junit4 = "androidx.compose.ui:ui-test-junit4"
    const val mockk = "io.mockk:mockk:${mockkVersion}"
    const val arch = "androidx.arch.core:core-testing:${archVersion}"
}

object Mapper {
    private const val mapstructVersion = "1.4.2.Final"
    private const val testVersion = "1.1.3"
    private const val espressoVersion = "3.4.0"

    const val mapstruct = "org.mapstruct:mapstruct:${mapstructVersion}"
    const val core = "org.mapstruct:mapstruct-processor:${mapstructVersion}"
}

object OkHttp {
    private const val okHttpVersion = "4.9.0"

    const val core = "com.squareup.okhttp3:okhttp:${okHttpVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${okHttpVersion}"
    const val mockWebserver = "com.squareup.okhttp3:mockwebserver:${okHttpVersion}"
}

object Paging {
    private const val pagingRuntimeVersion = "3.1.1"
    private const val pagingVersion = "2.4.2"

    const val runtime = "androidx.paging:paging-runtime-ktx:${pagingRuntimeVersion}"
    const val paging = "androidx.room:room-paging:${pagingVersion}"
}

object Compose {
    private const val activityVersion = "1.4.0"
    private const val navigationVersion = "2.7.6"
    private const val bomVersion = "2023.03.00"

    const val activity = "androidx.activity:activity-compose:${activityVersion}"
    const val navigation = "androidx.navigation:navigation-compose:${navigationVersion}"
    const val composeUI = "androidx.compose.ui:ui"
    const val composeGraphics = "androidx.compose.ui:ui-graphics"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial = "androidx.compose.material3:material3"
    const val composeTooling = "androidx.compose.ui:ui-tooling"
    const val composeManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeBom = "androidx.compose:compose-bom:${bomVersion}"
}
