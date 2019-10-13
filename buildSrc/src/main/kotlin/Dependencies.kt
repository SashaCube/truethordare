const val kotlinVersion = "1.3.41"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.5.1"
        const val googleService = "4.3.2"
        const val navigationSafeArgs = "2.1.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val googleServices = "com.google.gms:google-services:${Versions.googleService}"
    const val gmsGoogleServices = "com.google.gms.google-services"

    object Nav {
        const val saveArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationSafeArgs}"
        const val saveArgsPlugin = "androidx.navigation.safeargs.kotlin"
    }
}

object Kotlin {
    const val kotlinAndroid = "kotlin-android"
    const val extensions = "kotlin-android-extensions"
    const val kapt = "kotlin-kapt"
}

object Android {
    const val application = "com.android.application"
}

object AndroidSdk {
    const val min = 17
    const val compile = 29
    const val target = 29
}

object Libs {
    private object Versions {

        const val appCompat = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val material = "1.0.0"

        const val dagger = "2.24"
        const val jsr250 = "1.0"

        const val lifecycleExtension = "2.1.0"

        const val firebaseCore = "17.2.0"
        const val firebaseStore = "20.2.0"

        const val anko = "0.10.8"

        const val multidex = "1.0.3"

        const val nav = "2.1.0"
    }

    const val multidex = "com.android.support:multidex:${Versions.multidex}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val lifecycleExtension =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtension}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"

    object UI {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Dagger2 {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val jsr250Api = "javax.annotation:jsr250-api:${Versions.jsr250}"
        const val androidSupport =
            "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val androidProcessor =
            "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object Firebase {
        const val core = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
        const val store = "com.google.firebase:firebase-firestore:${Versions.firebaseStore}"
    }

    object Anko {
        const val commons = "org.jetbrains.anko:anko-commons:${Versions.anko}"
        const val appCompat = "org.jetbrains.anko:anko-appcompat-v7:${Versions.anko}"
        const val sdk25 = "org.jetbrains.anko:anko-sdk25:${Versions.anko}"
    }

    object Nav {
        const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
        const val uiKtx =  "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    }
}

object TestLibs {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.1.0-alpha4"
        const val espresso = "3.1.0-alpha4"
        const val mockito = "2.23.0"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    object Mockito {
        const val core = "org.mockito:mockito-core:${Versions.mockito}"
        const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    }
}