plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
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

tasks.register("generateSnapIt"){
    dependsOn("generateSnapItTests")
    dependsOn("recordPaparazzi")
}

tasks.register("generateSnapItTests") {
    dependsOn("assembleDebug")
    doLast{
        copy {
            from("build/generated/ksp/debug/kotlin/com/adbsalam")
            into("src/test/java/com/adbsalam/greetings")
            filter { line -> line.replace("//", "") }
        }
    }
}

dependencies {
    ksp(project(":processor"))
    implementation(project(":annotations"))

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.material.three)
    implementation(libs.junit)
    testImplementation(project(":testing"))

}