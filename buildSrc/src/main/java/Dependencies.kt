object ApplicationId {
    val id = "io.seekord.sebastian"
}

object Releases {
    val version_code = 1
    val version_name = "0.0.1"
}

object Sdk {
    val min = 19
    val target = 27
    val compile = 27
}

object Versions {

    object Core {
        val gradle = "3.1.1"
        val koltin = "1.2.21"
        val coroutines = "0.22.5"
        val dagger = "2.13"
    }

    object Rx {
        val java = "2.1.7"
        val android = "2.0.1"
    }

    object Network {
        val retrofit = "2.4.0"
        val okhttp = "3.10.0"
        val tikxml = "0.8.13"
    }

    object Support {
        val support = "27.1.0"
        val constraint_layout = "1.0.2"
    }

    object Ui {
        val material_edit_text = "2.1.4"
    }

    object Testing {
        val junit = "4.12"
        val mockito = "2.13.0"
    }

    object Other {
        val cicerone = "3.0.0"
        val moxy = "1.5.3"
        val timber = "4.6.0"
        val joda_time = "2.9.9.3"
    }

}

object Libraries {

    object Core {
        val gradle = "com.android.tools.build:gradle:${Versions.Core.gradle}"

        val data_binding = "com.android.databinding:compiler:${Versions.Core.gradle}"

        val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jre7:${Versions.Core.koltin}"
        val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Core.koltin}"

        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Core.coroutines}"
        val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Core.coroutines}"

        val dagger = "com.google.dagger:dagger:${Versions.Core.dagger}"
        val dagger_android = "com.google.dagger:dagger-android:${Versions.Core.dagger}"
        val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.Core.dagger}"
        val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.Core.dagger}"
        val dagger_compiler_android = "com.google.dagger:dagger-android-processor:${Versions.Core.dagger}"
    }

    object Rx {
        val java = "io.reactivex.rxjava2:rxjava:${Versions.Rx.java}"
        val android = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.android}"
    }

    object Network {
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        val retrofit_converter_scalars = "com.squareup.retrofit2:converter-scalars:${Versions.Network.retrofit}"

        val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Network.okhttp}"
        val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.okhttp}"

        val tikxml = "com.tickaroo.tikxml:core:${Versions.Network.tikxml}"
        val tikxml_annotations = "com.tickaroo.tikxml:annotation:${Versions.Network.tikxml}"
        val tikxml_converter = "com.tickaroo.tikxml:retrofit-converter:${Versions.Network.tikxml}"
        val tikxml_compiler = "com.tickaroo.tikxml:processor:${Versions.Network.tikxml}"
    }

    object Support {
        val appcompat = "com.android.support:appcompat-v7:${Versions.Support.support}"
        val design = "com.android.support:design:${Versions.Support.support}"
        //        val recycler_view = "com.android.support:recyclerview-v7:${Versions.Support.support}"
        val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.Support.constraint_layout}"
    }

    object Ui {
        val material_edit_text = "com.rengwuxian.materialedittext:library:${Versions.Ui.material_edit_text}"
    }

    object Testing {
        val junit = "junit:junit:${Versions.Testing.junit}"
        val mockito = "org.mockito:mockito-core:${Versions.Testing.mockito}"
    }

    object Other {
        val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.Other.cicerone}"
        val timber = "com.jakewharton.timber:timber:${Versions.Other.timber}"
        val joda_time = "net.danlew:android.joda:${Versions.Other.joda_time}"

        val moxy = "com.arello-mobile:moxy:${Versions.Other.moxy}"
        val moxy_appcompat = "com.arello-mobile:moxy-app-compat:${Versions.Other.moxy}"
        val moxy_compiler = "com.arello-mobile:moxy-compiler:${Versions.Other.moxy}"
    }

}
