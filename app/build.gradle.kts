plugins {
    `maven-publish`
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.foundation.foundationpoc"
    compileSdk {
        version = release(35)
    }

    defaultConfig {
        minSdk = 23
        targetSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // The publishing block in android is primarily for libraries. 
    // If you convert this module to a library, this configuration helps prepare the release component.
     publishing {
         singleVariant("release") {
             withSourcesJar()
         }
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
            // This now works because it is a library
            afterEvaluate {
                from(components["release"])
            }

            groupId = "com.foundation"
            artifactId = "foundationpoc"
            version = "0.0.8"
        }
    }
}
