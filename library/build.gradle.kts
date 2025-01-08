import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("net.nemerosa.versioning")
    `maven-publish`
}

val group = "fe.android.compose.dialog.helper"

android {
    namespace = group.toString()
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
    }

    kotlin {
        jvmToolchain(Version.JVM)
//        explicitApi()
    }

    dependencies {
        implementation(platform(AndroidX.compose.bom))
        implementation(AndroidX.compose.material3)
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
