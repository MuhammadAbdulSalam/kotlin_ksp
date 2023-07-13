plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    id("SnapIt")
}

android {
    namespace = "com.adbsalam.greetings"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin.sourceSets.main {
    kotlin.srcDirs(
        file("build/generated/ksp/kotlin"),
    )
}

ksp {
    arg("ignoreGenericArgs", "false")
}

tasks.register("snapItRecord") {
    dependsOn("recordPaparazzi")
}

snapIt {
    testDir("com/adbsalam/greetings")
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.material.three)
    implementation(libs.junit)
}