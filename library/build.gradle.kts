import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
    id("net.nemerosa.versioning")
}

android {
    namespace = "fe.android.compose.dialog.helper"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
    }
}

dependencies {
    api(AndroidX.compose.material3)
    api(AndroidX.compose.runtime)
    api(AndroidX.navigation.compose)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "fe.compose-dialog-helper"
            version = versioning.info.tag ?: versioning.info.full

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
