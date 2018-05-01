package dependencies

object ApplicationId {
    const val id = "io.seekord.sebastian"
}

object Releases {
    const val version_code = 1
    const val version_name = "0.0.1"
}

object Sdk {
    const val min = 19
    const val target = 27
    const val compile = 27
}

object Versions {

    object Core {
        const val gradle = "3.1.1"
        const val koltin = "1.2.21"
        const val coroutines = "0.22.5"
        const val dagger = "2.13"
    }

    object Rx {
        const val java = "2.1.7"
        const val android = "2.0.1"
    }

    object Network {
        const val retrofit = "2.4.0"
        const val okhttp = "3.10.0"
        const val tikxml = "0.8.13"
    }

    object Support {
        const val support = "27.1.0"
        const val constraint_layout = "1.0.2"
    }

    object Ui {
        const val material_edit_text = "2.1.4"
    }

    object Testing {
        const val junit = "4.12"
        const val mockito = "2.13.0"
    }

    object Other {
        const val cicerone = "3.0.0"
        const val moxy = "1.5.3"
        const val timber = "4.6.0"
        const val joda_time = "2.9.9.3"
    }

}

object Libs {

    object Core {
        const val gradle = "com.android.tools.build:gradle:${Versions.Core.gradle}"

        const val data_binding = "com.android.databinding:compiler:${Versions.Core.gradle}"

        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jre7:${Versions.Core.koltin}"
        const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Core.koltin}"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Core.coroutines}"
        const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Core.coroutines}"

        const val dagger = "com.google.dagger:dagger:${Versions.Core.dagger}"
        const val dagger_android = "com.google.dagger:dagger-android:${Versions.Core.dagger}"
        const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.Core.dagger}"
        const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.Core.dagger}"
        const val dagger_compiler_android = "com.google.dagger:dagger-android-processor:${Versions.Core.dagger}"
    }

    object Rx {
        const val java = "io.reactivex.rxjava2:rxjava:${Versions.Rx.java}"
        const val android = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.android}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        const val retrofit_converter_scalars = "com.squareup.retrofit2:converter-scalars:${Versions.Network.retrofit}"

        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Network.okhttp}"
        const val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.okhttp}"

        const val tikxml = "com.tickaroo.tikxml:core:${Versions.Network.tikxml}"
        const val tikxml_annotations = "com.tickaroo.tikxml:annotation:${Versions.Network.tikxml}"
        const val tikxml_converter = "com.tickaroo.tikxml:retrofit-converter:${Versions.Network.tikxml}"
        const val tikxml_compiler = "com.tickaroo.tikxml:processor:${Versions.Network.tikxml}"
    }

    object Support {
        const val appcompat = "com.android.support:appcompat-v7:${Versions.Support.support}"
        const val design = "com.android.support:design:${Versions.Support.support}"
        //        const val recycler_view = "com.android.support:recyclerview-v7:${Versions.Support.support}"
        const val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.Support.constraint_layout}"
    }

    object Ui {
        const val material_edit_text = "com.rengwuxian.materialedittext:library:${Versions.Ui.material_edit_text}"
    }

    object Testing {
        const val junit = "junit:junit:${Versions.Testing.junit}"
        const val mockito = "org.mockito:mockito-core:${Versions.Testing.mockito}"
    }

    object Other {
        const val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.Other.cicerone}"
        const val timber = "com.jakewharton.timber:timber:${Versions.Other.timber}"
        const val joda_time = "net.danlew:android.joda:${Versions.Other.joda_time}"

        const val moxy = "com.arello-mobile:moxy:${Versions.Other.moxy}"
        const val moxy_appcompat = "com.arello-mobile:moxy-app-compat:${Versions.Other.moxy}"
        const val moxy_compiler = "com.arello-mobile:moxy-compiler:${Versions.Other.moxy}"
    }

}
