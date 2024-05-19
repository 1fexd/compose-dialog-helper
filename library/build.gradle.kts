import de.fayard.refreshVersions.core.versionFor

plugins {
    id(libs.plugins.com.android.library)
    id(libs.plugins.org.jetbrains.kotlin.android)
    `maven-publish`
    id(libs.plugins.net.nemerosa.versioning)
}

val group = "fe.android.compose.dialog.helper"

android {
    namespace = group
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
    }

    kotlin {
        jvmToolchain(Version.JVM)
//        explicitApi()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
    }

    dependencies {
        implementation(AndroidX.compose.bom)
        implementation("androidx.compose.material3:material3:1.2.1")
        implementation(AndroidX.compose.ui)
        implementation(AndroidX.navigation.compose)
    }

    publishing {
        multipleVariants {
            allVariants()
            withSourcesJar()
        }
    }
}

publishing.publish(project, "com.github.1fexd",  "compose-dialog-helper",versioning.info.tag ?: versioning.info.full, PublicationComponent.RELEASE)
