@file:Suppress("unused")

/*
    Put everything into a package
    so we can use nested Objects (e.g. Libs.Core.*)
 */
package dependencies


object AndroidPlugin {
    const val sdk_version_min = 21
    const val sdk_version_target = 28
    const val sdk_version_compile = 28

    const val version_code = 1
    const val version_name = "0.0.1"

    const val application_id = "com.nikolaykul.sebastian"
}


object BuildPlugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.Core.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Core.kotlin}"
}


object Versions {

    object Core {
        const val gradle = "3.2.1"
        const val kotlin = "1.3.10"
        const val coroutines = "1.0.1"

        const val rx_java = "2.1.7"
        const val rx_android = "2.0.1"

        const val dagger = "2.16"
        const val assisted_inject = "0.3.2"

        const val room = "1.1.1"
        const val lifecycle = "1.1.1"
    }

    object Network {
        const val retrofit = "2.5.0"
        const val okhttp = "3.12.0"
        const val tikxml = "0.8.13"
    }

    object Support {
        const val support = "28.0.0"
        const val constraint_layout = "1.1.2"
    }

    object Other {
        const val cicerone = "3.0.0"
        const val timber = "4.7.1"
        const val joda_time = "2.9.9.3" // migrate to ThreeTenABP
        const val glide = "4.8.0"
    }

    object Debug {
        const val stetho = "1.5.0"
        const val leak_canary = "1.6.2"
    }

    object Test {
        const val junit = "4.12"
        const val mockito = "2.23.0"
    }

}


object Libs {

    object Core {
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib"

        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Core.coroutines}"
        const val coroutines_android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Core.coroutines}"

        const val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.Core.rx_java}"
        const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.Core.rx_android}"

        const val dagger = "com.google.dagger:dagger:${Versions.Core.dagger}"
        const val dagger_android = "com.google.dagger:dagger-android:${Versions.Core.dagger}"
        const val dagger_android_support =
            "com.google.dagger:dagger-android-support:${Versions.Core.dagger}"
        const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.Core.dagger}"
        const val dagger_compiler_android =
            "com.google.dagger:dagger-android-processor:${Versions.Core.dagger}"

        const val assisted_inject_annotations =
            "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.Core.assisted_inject}"
        const val assisted_inject_processor =
            "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.Core.assisted_inject}"

        const val room_runtime = "android.arch.persistence.room:runtime:${Versions.Core.room}"
        const val room_compiler = "android.arch.persistence.room:compiler:${Versions.Core.room}"
        const val room_rx = "android.arch.persistence.room:rxjava2:${Versions.Core.room}"

        const val lifecycle_ext = "android.arch.lifecycle:extensions:${Versions.Core.lifecycle}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        const val retrofit_converter_scalars =
            "com.squareup.retrofit2:converter-scalars:${Versions.Network.retrofit}"

        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Network.okhttp}"
        const val okhttp_logging =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Network.okhttp}"

        const val tikxml = "com.tickaroo.tikxml:core:${Versions.Network.tikxml}"
        const val tikxml_annotations = "com.tickaroo.tikxml:annotation:${Versions.Network.tikxml}"
        const val tikxml_converter =
            "com.tickaroo.tikxml:retrofit-converter:${Versions.Network.tikxml}"
        const val tikxml_compiler = "com.tickaroo.tikxml:processor:${Versions.Network.tikxml}"
    }

    object Support {
        const val appcompat = "com.android.support:appcompat-v7:${Versions.Support.support}"
        const val design = "com.android.support:design:${Versions.Support.support}"
        const val constraint_layout =
            "com.android.support.constraint:constraint-layout:${Versions.Support.constraint_layout}"
    }

    object Other {
        const val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.Other.cicerone}"
        const val timber = "com.jakewharton.timber:timber:${Versions.Other.timber}"
        const val joda_time = "net.danlew:android.joda:${Versions.Other.joda_time}"

        const val glide = "com.github.bumptech.glide:glide:${Versions.Other.glide}"
        const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.Other.glide}"
    }

    object Debug {
        const val stetho = "com.facebook.stetho:stetho:${Versions.Debug.stetho}"
        const val stetho_okhttp = "com.facebook.stetho:stetho-okhttp3:${Versions.Debug.stetho}"

        const val leak_canary_debug =
            "com.squareup.leakcanary:leakcanary-android:${Versions.Debug.leak_canary}"
        const val leak_canary_release =
            "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.Debug.leak_canary}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val mockito = "org.mockito:mockito-core:${Versions.Test.mockito}"
    }

}
