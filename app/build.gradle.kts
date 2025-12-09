plugins {
    `maven-publish`
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.natura.foundationpoc"
    compileSdk {
        version = release(35)
    }

    defaultConfig {
        applicationId = "com.natura.foundationpoc"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "0.0.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // The publishing block in android is primarily for libraries. 
    // If you convert this module to a library, this configuration helps prepare the release component.
    // publishing {
    //     singleVariant("release") {
    //         withSourcesJar()
    //     }
    // }

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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            // The "release" component is typically available for Android Libraries (com.android.library).
            // Since this is an Application module (com.android.application), you cannot use components["release"] directly.
            // If you intend to publish an APK or AAB, you need to define artifacts manually.
            // Example: artifact(tasks.getByName("bundleRelease"))
            
            // from(components["release"]) 

            groupId = "com.natura"
            artifactId = "foundationpoc"
            version = "0.0.5"
        }
    }
}
