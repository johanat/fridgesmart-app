plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "fridgeSmart.fridgesmart"
    compileSdk = 34

    defaultConfig {
        applicationId = "fridgeSmart.fridgesmart"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    /*viewBinding {
        enabled = true
    }*/
}

dependencies {

    implementation(libs.room.runtime)
    implementation(libs.room.common.jvm)
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.work:work-runtime:2.9.0")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    annotationProcessor ("androidx.room:room-compiler:2.4.0")
    implementation ("org.json:json:20210307")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation ("androidx.room:room-runtime:2.4.0")
    implementation ("androidx.cardview:cardview:1.0.0")

    // If this project only uses Java source, use the Java annotationProcessor
    // No additional plugins are necessary
    annotationProcessor(libs.room.compiler)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.room.ktx)

    // optional - RxJava2 support for Room
    implementation(libs.room.rxjava2)

    // optional - RxJava3 support for Room
    implementation(libs.room.rxjava3)

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation(libs.room.guava)

    // optional - Test helpers
    testImplementation(libs.room.testing)

    // optional - Paging 3 Integration
    implementation(libs.room.paging)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    

}