import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.buildlogic.application)
    alias(libs.plugins.buildlogic.firebase)
    alias(libs.plugins.compose.compiler)
}


val secretsPropertiesFile = rootProject.file("./app/secrets.properties")
val secretProperties = Properties()
val customKeystoreFile = file("keystore.jks")
if (secretsPropertiesFile.exists())
    secretProperties.load(FileInputStream(secretsPropertiesFile))
else throw FileNotFoundException("Secrets file not found. Please create a secrets.properties file in the app directory.")

android {
    namespace = "es.sebas1705.androidmodapp"

    defaultConfig {
        applicationId = "es.sebas1705.androidmodapp"
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = customKeystoreFile
            storePassword = secretProperties["signing_keystore_password"] as String?
            keyAlias = secretProperties["signing_key_alias"] as String?
            keyPassword = secretProperties["signing_keystore_password"] as String?
        }
        getByName("debug") {
            if (customKeystoreFile.exists()) {
                storeFile = customKeystoreFile
                storePassword = secretProperties["signing_keystore_password"] as String?
                keyAlias = secretProperties["signing_key_alias"] as String?
                keyPassword = secretProperties["signing_keystore_password"] as String?
            } else {
                logger.lifecycle("Using default Android debug signing config (app/keystore.jks not found).")
            }
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }


    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
        }
    }

    packaging {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            resources.excludes.add("META-INF/versions/9/OSGI-INF/MANIFEST.MF")
        }
    }

}

dependencies {
    api(projects.core.resources)
    api(projects.domain.services)
    api(projects.feature.main)

    debugImplementation(libs.leakcanary.android)
}