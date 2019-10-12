plugins {
    id(Android.application)
    id(Kotlin.kotlinAndroid)
    id(Kotlin.extensions)
    id(Kotlin.kapt)
    id(BuildPlugins.gmsGoogleServices)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "com.cubesoft.oleksandr.havryliuk.trueth_or_dare"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 4
        versionName = "1.3"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {

        }
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    implementation(Libs.kotlinStdLib)
    implementation(Libs.kotlinReflect)
    implementation(Libs.lifecycleExtension)

    implementation(Libs.multidex)

    implementation(Libs.UI.appCompat)
    implementation(Libs.UI.material)
    implementation(Libs.UI.constraintLayout)

    implementation(Libs.Dagger2.dagger)
    implementation(Libs.Dagger2.androidSupport)
    compileOnly(Libs.Dagger2.jsr250Api)
    kapt(Libs.Dagger2.compiler)
    kapt(Libs.Dagger2.androidProcessor)
    annotationProcessor(Libs.Dagger2.androidProcessor)

    implementation(Libs.Firebase.core)
    implementation(Libs.Firebase.store)

    implementation(Libs.Anko.commons)
    implementation(Libs.Anko.appCompat)
    implementation(Libs.Anko.sdk25)

    testImplementation(TestLibs.junit4)
    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.espresso)

    testImplementation(TestLibs.Mockito.core)
    androidTestImplementation(TestLibs.Mockito.mockitoAndroid)
}